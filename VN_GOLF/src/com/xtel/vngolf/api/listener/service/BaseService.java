package com.xtel.vngolf.api.listener.service;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Consumes({"application/json"})
@Produces({"application/json"})
@Path("/*")
public class BaseService {
	
	@Context
	protected HttpServletRequest httpServletRequest;
	
}
