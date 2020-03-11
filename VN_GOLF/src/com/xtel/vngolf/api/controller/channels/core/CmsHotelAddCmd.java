package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseBodyReqTypeCmd;
import com.xtel.vngolf.api.listener.request.CmsHotelAddReq;
import com.xtel.vngolf.api.model.DbCmsHotelAddCmd;

public class CmsHotelAddCmd extends AbsApiBaseBodyReqTypeCmd{
	private CmsHotelAddReq obj;

	public CmsHotelAddCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
		super(httpServletRequest, jsonRequest, classRequest);
	}

	@Override
	protected void executeCmd() throws Exception {
		DbCmsHotelAddCmd dbcmd = new DbCmsHotelAddCmd(transid,channel,obj);
        executeDbCmd(dbcmd);
        setResponse(dbcmd.getCode(),dbcmd.getMessage());
		
	}
	protected boolean validateData() {
		this.obj = (CmsHotelAddReq) objRequest;
		return true;
	}
	
	protected boolean validateToken() {
		return true;
	}
}
