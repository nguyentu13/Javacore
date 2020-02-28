package com.xtel.restful.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("user/")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class UserService {
	@Path("1")
	@GET
	public Response getDetailUser() {
		return Response.ok().entity(new Gson().toJson(new User(1,"truong_nguyen"))).build();	
	}
}
