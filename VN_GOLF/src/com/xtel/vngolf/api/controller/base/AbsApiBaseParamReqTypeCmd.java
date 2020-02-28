package com.xtel.vngolf.api.controller.base;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.listener.response.BaseResp;
import com.xtel.vngolf.api.listener.response.EnumRsCode;

public abstract class AbsApiBaseParamReqTypeCmd extends AbsApiBaseCmd {
	
	public AbsApiBaseParamReqTypeCmd(HttpServletRequest httpServletRequest, String channel, String transid) {
		super(httpServletRequest, channel, transid);
	}
	
	@Override
	public void execute() {
		
		logger.debug(String.format("BEGIN_CMD transid: %s, channel: %s, request: %s", transid, channel, logRequest()));
		
		if(!validateToken()) {
			createRespone();
			return;
		}
		
		if(!validateData()) {
			if(objResponse == null) {
				objResponse = new BaseResp(EnumRsCode.REQUEST_INVALID.getCode(), EnumRsCode.REQUEST_INVALID.getMessage());
			}
			
			createRespone();
			return;
		}
		
		try {
			executeCmd();
		} catch (Exception e) {
			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(), EnumRsCode.UNKNOWN_ERROR.getMessage());
			logger.warn(String.format("transid: %s, channel: %s, request: %s, exception: %s", 
					transid, channel, logRequest(), e.getMessage()), e);
		}

		createRespone();
		
		logger.debug(
				String.format("END_CMD transid: %s, channel: %s, request: %s, cmd: %s, time: %s, response: %s", 
						transid, channel, logRequest(), this.toString(), 
						logTimeExecute(),
						logResponse()));
	}

}
