package com.xtel.vngolf.api.listener.service.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsListEventCmd;
import com.xtel.vngolf.api.controller.channels.core.EventGetListXXXCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

@Path("view/event")
public class EventServices extends BaseService{
	
//	@Path("list")
//	@GET
//	public Response getListEvent(@QueryParam("channel") String channel,
//			@QueryParam("transid") String transid,
//			@QueryParam("page_index") int page_index,
//			@QueryParam("page_size") int page_size,
//			@QueryParam("lang_id") int lang_id
//			) {
//		CmsListEventCmd cmd = new CmsListEventCmd(httpServletRequest, channel, transid, page_index, page_size, lang_id);
//		cmd.execute();
//		return cmd.getResponse();
//	}

	@Path("list")
	@GET
	public Response  getListXXX(@QueryParam("transid") String transid,
							   @QueryParam("channel") String channel,
							   @QueryParam("page_index") int page_index,
							   @QueryParam("page_size") int page_size,
							   @QueryParam("order_by") String order_by,
							   @QueryParam("order_type") String order_type,
							   @QueryParam("category_id") int category_id,
							   @QueryParam("date") String date,
							   @QueryParam("author") String author,
							   @QueryParam("language_id") int language_id){
		EventGetListXXXCmd cmd = new EventGetListXXXCmd(httpServletRequest,channel,transid,
				page_index,page_size,order_by,order_type,category_id,date,author,language_id);
		cmd.execute();
		return cmd.getResponse();
	}
}
