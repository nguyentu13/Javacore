package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicItemListData;
import com.xtel.vngolf.api.model.DbCmsGolfCourseGetListCmd;

public class CmsGolfCourseListCmd extends AbsApiBaseParamReqTypeCmd {
	private int page_index;
	private int page_size;
	private int lang_id;

	public CmsGolfCourseListCmd(HttpServletRequest httpServletRequest, String channel, String transid, int page_index,
			int page_size, int lang_id) {
		super(httpServletRequest, channel, transid);
		this.page_index = page_index;
		this.page_size = page_size;
		this.lang_id = lang_id;
	}

	@Override
	protected void executeCmd() throws Exception {

		DbCmsGolfCourseGetListCmd dbCmd = new DbCmsGolfCourseGetListCmd(transid, channel, page_index, page_size,
				lang_id);
		executeDbCmd(dbCmd);
		setResponse(dbCmd.getCode(), dbCmd.getMessage(), new BasicItemListData(dbCmd.getList()));
	}

	@Override
	protected boolean validateToken() {
		return true;
	}
}
