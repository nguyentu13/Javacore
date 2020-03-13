package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicItemData;
import com.xtel.vngolf.api.listener.response.data.BasicItemListData;
import com.xtel.vngolf.api.listener.service.cms.GolfCourseServices;
import com.xtel.vngolf.api.model.DbCmsGolfCourseDetailCmd;

public class CmsGolfCourseDetailCmd extends AbsApiBaseParamReqTypeCmd{

	private int course_id;
	private int lang_id;
	private int max_img;
	
	
	
	public CmsGolfCourseDetailCmd(HttpServletRequest httpServletRequest, String channel, String transid, int course_id,
		int lang_id, int max_img) {
		super(httpServletRequest, channel, transid);
		this.course_id = course_id;
		this.lang_id = lang_id;
		this.max_img = max_img;
	}

	@Override
	protected void executeCmd() throws Exception {
		DbCmsGolfCourseDetailCmd dbCmd = new DbCmsGolfCourseDetailCmd(transid, channel, course_id, lang_id, max_img);
		executeDbCmd(dbCmd);
		setResponse(dbCmd.getCode(), dbCmd.getMessage(), new BasicItemData(dbCmd.getGolf_course()));
	}
	
	protected boolean validateToken() {
		return true;
	}
}
