package com.xtel.vngolf.api.listener.service.cms;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.UserCmsGetListCmd;
import com.xtel.vngolf.api.controller.channels.core.UserCmsInsertCmd;
import com.xtel.vngolf.api.listener.request.CmsUserInfoReq;
import com.xtel.vngolf.api.controller.channels.core.UserCmsLoginCmd;
import com.xtel.vngolf.api.listener.request.LoginReq;
import com.xtel.vngolf.api.listener.service.BaseService;



@Path("cms/user")
public class UserService extends BaseService {
	@GET
	@Path("list")
	public Response getList(@QueryParam("channel") String channel, 
							@QueryParam("transid") String transid,
							@QueryParam("page_index") int page_index, 
							@QueryParam("page_size") int page_size,
							@QueryParam("order_by") String order_by,
							@QueryParam("order_type") String order_type) {
		UserCmsGetListCmd cmd = new UserCmsGetListCmd(httpServletRequest, channel, transid, page_index, page_size, order_by,order_type);
		cmd.execute();
		return cmd.getResponse();
	}
	
	
	@POST
	@Path("login")
	public Response login(String jsonRequest) {
		UserCmsLoginCmd cmd = new UserCmsLoginCmd(httpServletRequest, jsonRequest, LoginReq.class);
		cmd.execute();
		return cmd.getResponse();
	}
	
	@POST
	@Path("add")
	public Response add(String jsonRequest) {
		UserCmsInsertCmd cmd = new UserCmsInsertCmd(httpServletRequest, jsonRequest, CmsUserInfoReq.class);
		cmd.execute();
		return cmd.getResponse();
	}
}
