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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import com.google.gson.Gson;
import com.tbv.utils.webservice.EntityToJson;

/**
*
* @author trongbv
*/
public class JsonClientHelper {
	
	protected URI baseUrl;
	protected int connectTimeout;
	protected int readTimeout;
	protected int poolSize;
	protected Client client;
	
	public JsonClientHelper() {
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
	
	public RequestJson createMethod(String path) {
		return new RequestJson(client, path, null);
	}
	
	public RequestJson createMethod(String path, HashMap<String, Object> headers) {
		return new RequestJson(client, path, headers);
	}
	
	public class RequestJson {
		
		private final Client _client;
		private UriBuilder _uriBuilder;
		private HashMap<String, Object> _headers;
		private final Gson gson = new Gson();
		
		public RequestJson(Client client, String path, HashMap<String, Object> headers) {
			_client = client;
			_uriBuilder = UriBuilder.fromUri(baseUrl);
			_uriBuilder = _uriBuilder.path(path);
			_headers = headers;
		}

		public <T> T post(Object input, Class<T> returnType) {
			String resp = post(new EntityToJson(input).toString());
			return gson.fromJson(resp, returnType);
		}

		public <T> T get(Class<T> returnType) {
			String resp = get();
			return gson.fromJson(resp, returnType);
		}

		public String post(String postBody) {
			return invoke("POST", postBody);
		}

		public String get() {
			return invoke("GET", null);
		}

		private String invoke(String method, String postBody) {

			WebTarget target = _client.target(_uriBuilder.toString());
			
			Response response = null;
			
			Builder builder = target.request(MediaType.APPLICATION_JSON_TYPE);
			
			if(_headers != null && !_headers.isEmpty()) {
				for(Map.Entry<String, Object> header: _headers.entrySet()) {
					if(header.getKey() != null && !header.getKey().isEmpty()) {
						builder.header(header.getKey(), header.getValue());
					}
		        }
			}
			
			if(method.equalsIgnoreCase("POST")) {
				response = builder.post(Entity.entity(postBody, MediaType.APPLICATION_JSON));
			} else {
				response = builder.get();
			}

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
