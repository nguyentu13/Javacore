package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.model.DbCmsHotelGetDetailCmd;

public class CmsHotelGetDetailCmd extends AbsApiBaseParamReqTypeCmd{
	private int hotel_id;

	public CmsHotelGetDetailCmd(HttpServletRequest httpServletRequest, String channel, String transid,int hotel_id) {
		super(httpServletRequest, channel, transid);
		this.hotel_id=hotel_id;
	}

	@Override
	protected void executeCmd() throws Exception {
		DbCmsHotelGetDetailCmd dbCmd = new DbCmsHotelGetDetailCmd(channel,transid,hotel_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(),dbCmd.getMessage(),dbCmd.getObj());
	}
	
	protected boolean validateToken() {
		return true;
	}
}
