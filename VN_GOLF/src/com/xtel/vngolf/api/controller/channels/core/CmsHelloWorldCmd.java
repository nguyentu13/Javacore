package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.tbv.utils.textbase.StringUtil;
import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.model.DbCmsHelloWorldCmd;

public class CmsHelloWorldCmd extends AbsApiBaseParamReqTypeCmd{
	private String username;
	
	public CmsHelloWorldCmd(HttpServletRequest httpServletRequest, String channel, String transid,String username) {
		super(httpServletRequest, channel, transid);
		// TODO Auto-generated constructor stub
		this.username = username;
	}

	@Override
	protected void executeCmd() throws Exception {
		// TODO Auto-generated method stub
		DbCmsHelloWorldCmd dbCmd = new DbCmsHelloWorldCmd(transid,channel,username);
		executeDbCmd(dbCmd);
		setResponse(dbCmd.getCode(),dbCmd.getMessage(),dbCmd.getResult());
	}
	
	protected boolean validateData() {
		if(StringUtil.isNullOrEmpty(username)) {
			return false;
		}
		return true;
	}
	
	protected boolean validateToken() {
		return true;
	}
	

}
