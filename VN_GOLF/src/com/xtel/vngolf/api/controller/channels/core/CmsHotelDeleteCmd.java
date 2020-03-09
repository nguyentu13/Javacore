package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.model.DbCmsHotelDeleteCmd;

public class CmsHotelDeleteCmd extends AbsApiBaseParamReqTypeCmd {
	private int hotel_id;

	public CmsHotelDeleteCmd(HttpServletRequest httpServletRequest, String channel, String transid, int hotel_id) {
		super(httpServletRequest, channel, transid);
		this.hotel_id = hotel_id;
	}

	@Override
	protected void executeCmd() throws Exception {
		DbCmsHotelDeleteCmd dbCmd = new DbCmsHotelDeleteCmd(transid,channel,hotel_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(),dbCmd.getMessage());
	}
	
	protected boolean validateToken() {
		return true;
	}

}
