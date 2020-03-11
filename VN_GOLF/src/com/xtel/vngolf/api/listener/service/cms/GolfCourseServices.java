package com.xtel.vngolf.api.listener.service.cms;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsGolfCourseListCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

@Path("view/golfcourse")
public class GolfCourseServices extends BaseService{
	
	@Path("list")
	@GET
	public Response getList(@QueryParam("channel") String channel, 
			@QueryParam("transid") String transid,
			@QueryParam("page_index") int page_index,
			@QueryParam("page_size") int page_size
			) {
		CmsGolfCourseListCmd cmd = new CmsGolfCourseListCmd(httpServletRequest, channel, transid, page_index , page_size);
		cmd.execute();
		return cmd.getResponse();
		
	}
}
