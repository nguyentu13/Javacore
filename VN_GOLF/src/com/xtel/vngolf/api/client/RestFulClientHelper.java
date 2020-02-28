package com.xtel.vngolf.api.client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.google.gson.Gson;
import com.tbv.utils.webservice.EntityToJson;

/**
*
* @author trongbv
*/
public class RestFulClientHelper {
	
	protected URI baseUrl;
	protected int connectTimeout;
	protected int readTimeout;
	protected int poolSize;
	protected Client client;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	public RestFulClientHelper() {
		super();
	}

	public void init(String uri, int connectTimeout, int readTimeout, int poolSize) throws Exception {
		
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.baseUrl = new URI(uri);
		this.poolSize = poolSize;
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout);
		clientConfig.property(ClientProperties.READ_TIMEOUT, readTimeout);
		clientConfig.scheduledExecutorService(Executors.newScheduledThreadPool(poolSize));
		client = ClientBuilder.newClient(clientConfig);
	}
	
	public void setUrl(String uri) throws Exception {
		this.baseUrl = new URI(uri);
	}
	
	public ClientRequest createMethod(String path, Object mediaType) {
		return new ClientRequest(client, path, mediaType, null);
	}
	
	public ClientRequest createMethod(String path, Object mediaType, HashMap<String, Object> headers) {
		return new ClientRequest(client, path, mediaType, headers);
	}
	
	public class ClientRequest {
		
		private final Client _client;
		private UriBuilder _uriBuilder;
		private HashMap<String, Object> _headers;
		private final Gson gson = new Gson();
		private Object _mediaType;
		
		public ClientRequest(Client client, String path, Object mediaType, HashMap<String, Object> headers) {
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUrl);
			_uriBuilder = _uriBuilder.path(path);
			_mediaType = mediaType;
			_headers = headers;
		}

		public <T> T post(Object input, Class<T> returnType) throws Exception {
			String resp = post(new EntityToJson(input).toString());
			return gson.fromJson(resp, returnType);
		}

		public <T> T get(Class<T> returnType)  throws Exception {
			String resp = get();
			return gson.fromJson(resp, returnType);
		}

		public String post(Object postBody) throws Exception {
			return invoke("POST", postBody);
		}

		public String get()  throws Exception {
			return invoke("GET", null);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private String invoke(String method, Object postBody) throws Exception {

			String urlCall = _uriBuilder.toString();
			
			WebTarget target = _client.target(urlCall);
			
			Response response = null;
			
			Builder builder = null;
			if(_mediaType != null) {
				if(_mediaType instanceof String) {
					builder = target.request((String) _mediaType);
				} else {
					builder = target.request((MediaType) _mediaType);
				}
			} else {
				builder = target.request(MediaType.APPLICATION_JSON_TYPE);
			}
			
			
			if(_headers != null && !_headers.isEmpty()) {
				for(Map.Entry<String, Object> header: _headers.entrySet()) {
					if(header.getKey() != null && !header.getKey().isEmpty()) {
						builder.header(header.getKey(), header.getValue());
					}
		        }
			}
			
			logger.info(String.format("Client call URL: %s, method: %s, mediaType: %s", urlCall, method, _mediaType));
			
			if(method.equalsIgnoreCase("POST")) {
				
				Entity<?> entity = null;
				
				if(postBody instanceof MultivaluedMap) {
					entity = Entity.form((MultivaluedMap) postBody);
				} else {
					if(_mediaType != null) {
						if(_mediaType instanceof String) {
							entity =  Entity.entity(postBody, (String) _mediaType);
							
						} else {
							entity =  Entity.entity(postBody, (MediaType) _mediaType);
						}
					} else {
						entity = Entity.entity(postBody, MediaType.APPLICATION_JSON_TYPE);
					}
				}
				logger.info(String.format("Client call url: %s, method: %s, mediaType: %s, postBody: %s, entity: %s", 
						urlCall, method, _mediaType, postBody, entity));
				
				response = builder.post(entity);
			} else {
				response = builder.get();
			}
			
			logger.info("response: " + response);

			if (response.getStatus() >= 400) {
				throw new WebApplicationExceptionMessage(Response.status(response.getStatusInfo()).build());
			}
			String entity = response.readEntity(String.class);
			
			response.close();
			return entity;
		}

		public void queryParam(String key, Object value) {
			_uriBuilder.queryParam(key, new Object[] { value });
		}

		public void close() {
			if (_client != null) {
				try {
					_client.close();
				} catch (Exception localException) {
					
				}
			}
		}
	}
	
	private static class WebApplicationExceptionMessage extends WebApplicationException {
		
		private static final long serialVersionUID = 1L;

		private WebApplicationExceptionMessage(Response response) {
			super();
		}

		@Override
		public String getMessage() {
			Response response = getResponse();
			Response.Status status = Response.Status.fromStatusCode(response.getStatus());
			if (status != null) {
				return response.getStatus() + " " + status.getReasonPhrase();
			}
			return Integer.toString(response.getStatus());
		}

		@Override
		public String toString() {
			String s = "javax.ws.rs.WebApplicationException";
			String message = getLocalizedMessage();
			return s + ": " + message;
		}
	}
	
	
}
