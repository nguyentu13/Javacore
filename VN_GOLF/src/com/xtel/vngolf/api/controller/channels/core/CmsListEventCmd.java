package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicItemData;
import com.xtel.vngolf.api.listener.response.data.BasicItemListData;
import com.xtel.vngolf.api.model.DBCmsListEventCmd;

public class CmsListEventCmd extends AbsApiBaseParamReqTypeCmd{
	private int page_index;
	private int page_size;
	private int lang_id;
	
	
	public CmsListEventCmd(HttpServletRequest httpServletRequest, String channel, String transid, int page_index,
			int page_size, int lang_id) {
		super(httpServletRequest, channel, transid);
		this.page_index = page_index;
		this.page_size = page_size;
		this.lang_id = lang_id;
	}


	@Override
	protected void executeCmd() throws Exception {
		DBCmsListEventCmd dbCmd = new DBCmsListEventCmd(transid,channel,page_index,page_size,lang_id);
		executeDbCmd(dbCmd);
		setResponse(dbCmd.getCode(),dbCmd.getMessage(),new BasicItemListData(dbCmd.getList_event()));
		
	}
	
	protected boolean validateToken() {
		return true;
	}
	
}
