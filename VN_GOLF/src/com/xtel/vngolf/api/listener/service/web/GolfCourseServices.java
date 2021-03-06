package com.xtel.vngolf.api.listener.service.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtel.vngolf.api.controller.channels.core.CmsGolfCourseDetailCmd;
import com.xtel.vngolf.api.controller.channels.core.CmsGolfCourseListCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

@Path("view/golfcourse")
public class GolfCourseServices extends BaseService {

	@Path("list")
	@GET
	public Response getList(@QueryParam("channel") String channel, 
			@QueryParam("transid") String transid,
			@QueryParam("page_index") int page_index,
			@QueryParam("page_size") int page_size,
			@QueryParam("lang_id") int lang_id
			) {
		CmsGolfCourseListCmd cmd = new CmsGolfCourseListCmd(httpServletRequest, channel, transid, page_index , page_size, lang_id);
		cmd.execute();
		return cmd.getResponse();
		
	}

	@Path("detail")
	@GET
	public Response detailGolfCourse(
			@QueryParam("channel") String channel, 
			@QueryParam("transid") String transid,
			@QueryParam("course_id") int course_id,
			@QueryParam("lang_id")int lang_id,
			@QueryParam("max_img") int max_img
			) {
		CmsGolfCourseDetailCmd cmd = new CmsGolfCourseDetailCmd(httpServletRequest, channel, transid, course_id, lang_id, max_img);
		cmd.execute();
		return cmd.getResponse();
	}
}
