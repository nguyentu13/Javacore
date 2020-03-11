package com.xtel.vngolf.api.listener.service.cms;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsHotelAddCmd;
import com.xtel.vngolf.api.controller.channels.core.CmsHotelDeleteCmd;
import com.xtel.vngolf.api.controller.channels.core.CmsHotelGetDetailCmd;
import com.xtel.vngolf.api.controller.channels.core.CmsHotelGetListCmd;
import com.xtel.vngolf.api.controller.channels.core.CmsHotelUpdateCmd;
import com.xtel.vngolf.api.listener.request.CmsHotelAddReq;
import com.xtel.vngolf.api.listener.request.CmsHotelUpdateReq;
import com.xtel.vngolf.api.listener.service.BaseService;

@Path("cms/hotel")
public class HotelService extends BaseService {
	@Path("list")
	@GET
	public Response getList(@QueryParam("transid") String transid, 
							@QueryParam("channel") String channel,
							@QueryParam("page_index") int page_index, 
							@QueryParam("page_size") int page_size,
							@QueryParam("order_by") String order_by, 
							@QueryParam("order_type") String order_type,
							@QueryParam("q") String keyword) {
		CmsHotelGetListCmd cmd = new CmsHotelGetListCmd(httpServletRequest, channel, transid, page_index, page_size, order_by, order_type,keyword);
		cmd.execute();
		return cmd.getResponse();
	}
	
	@Path("detail")
	@GET
	public Response getDetail(@QueryParam("transid") String transid,
	                          @QueryParam("channel") String channel,
	                          @QueryParam("hotel_id") int hotel_id){
	    CmsHotelGetDetailCmd cmd = new CmsHotelGetDetailCmd(httpServletRequest,channel,transid,hotel_id);
	    cmd.execute();
	    return cmd.getResponse();
	}

	@Path("add")
	@POST
	public Response add(String body) {
		CmsHotelAddCmd cmd = new CmsHotelAddCmd(httpServletRequest, body, CmsHotelAddReq.class);
		cmd.execute();
		return cmd.getResponse();
	}

	@Path("update")
	@POST
	public Response update(String body) {
		CmsHotelUpdateCmd cmd = new CmsHotelUpdateCmd(httpServletRequest, body, CmsHotelUpdateReq.class);
		cmd.execute();
		return cmd.getResponse();
	}

	@Path("delete")
	@POST
	public Response delete(@QueryParam("transid") String transid, 
						   @QueryParam("channel") String channel,
						   @QueryParam("hotel_id") int hotel_id) {
		CmsHotelDeleteCmd cmd = new CmsHotelDeleteCmd(httpServletRequest, channel, transid, hotel_id);
		cmd.execute();
		return cmd.getResponse();
	}
}
