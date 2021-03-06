package com.xtel.vngolf.api.listener.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {
	protected final Log logger = LogFactory.getLog(this.getClass());

	private static boolean isPreflightRequest(ContainerRequestContext request) {
		return request.getHeaderString("Origin") != null && request.getMethod().equalsIgnoreCase("OPTIONS");
	}

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		if (isPreflightRequest(request)) {
			request.abortWith(Response.ok().build());
			return;
		}
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {

		if (request == null || request.getHeaderString("Origin") == null) {
			return;
		}

		if (isPreflightRequest(request)) {
			if (response != null) {
				response.getHeaders().add("Access-Control-Allow-Credentials", "true");
				response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
				response.getHeaders().add("Access-Control-Allow-Headers", "*");
				response.getHeaders().add("Content-Type", "application/json; charset=utf-8");
			}
		}

		if (response != null) {
			response.getHeaders().add("Access-Control-Allow-Origin", "*");
		}
	}
}
