package com.xtel.vngolf.api.listener.service.cms;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsListEventCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

@Path("view/event")
public class EventServices extends BaseService{
	
	@Path("list")
	@GET
	public Response getListEvent(@QueryParam("channel") String channel, 
			@QueryParam("transid") String transid,
			@QueryParam("page_index") int page_index,
			@QueryParam("page_size") int page_size,
			@QueryParam("lang_id") int lang_id
			) {
		CmsListEventCmd cmd = new CmsListEventCmd(httpServletRequest, channel, transid, page_index, page_size, lang_id);
		cmd.execute();
		return cmd.getResponse();		
	}
}
