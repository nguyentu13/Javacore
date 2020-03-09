package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseBodyReqTypeCmd;
import com.xtel.vngolf.api.listener.request.CmsHotelUpdateReq;
import com.xtel.vngolf.api.model.DbCmsHotelUpdateCmd;

public class CmsHotelUpdateCmd extends AbsApiBaseBodyReqTypeCmd{
	private CmsHotelUpdateReq obj;
	

	public CmsHotelUpdateCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
		super(httpServletRequest, jsonRequest, classRequest);
	}

	@Override
	protected void executeCmd() throws Exception {
		DbCmsHotelUpdateCmd dbCmd = new DbCmsHotelUpdateCmd(transid,channel,obj);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(),dbCmd.getMessage());	
	}
	
	protected boolean validateData() {
		this.obj = (CmsHotelUpdateReq) objRequest;
		return true;
		
	}
	
	protected boolean validateToken() {
		return true;
	}
}
