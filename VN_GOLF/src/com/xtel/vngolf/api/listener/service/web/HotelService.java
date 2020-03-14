package com.xtel.vngolf.api.listener.service.web;

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
							@QueryParam("channel") String channel,
							@QueryParam("page_index") int page_index,
							@QueryParam("page_size") int page_size,
							@QueryParam("order_by") String order_by,
							@QueryParam("order_type") String order_type) {
		CmsHotelGetListCmd cmd = new CmsHotelGetListCmd(httpServletRequest,channel,transid,
				page_index,page_size,order_by,order_type);
		cmd.execute();
		return cmd.getResponse();
	}
}
