package com.xtel.vngolf.api.listener.service.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsHelloWorldCmd;
import com.xtel.vngolf.api.listener.service.BaseService;
@Path("cms/hello_world")
public class HelloWorldService extends BaseService{
	@Path("/user")
	@GET
	public Response helloWorld(@QueryParam("transid")String transid,
			@QueryParam("channel") String channel,
			@QueryParam("username") String username) {
		
		CmsHelloWorldCmd cmd = new CmsHelloWorldCmd(httpServletRequest,channel,transid,username);
		cmd.execute();
		return cmd.getResponse();
	}
}
