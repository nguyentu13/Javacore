package com.xtel.vngolf.api.listener.service.cms;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsHotelGetListCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

@Path("cms/hotel")
public class HotelService extends BaseService{
	@Path("list")
	@GET
	public Response getList(@QueryParam("transid") String transid,
			@QueryParam("channel") String channel) {
		CmsHotelGetListCmd cmd = new CmsHotelGetListCmd(httpServletRequest,channel,transid);
		cmd.execute();
		return cmd.getResponse();
	}
}
