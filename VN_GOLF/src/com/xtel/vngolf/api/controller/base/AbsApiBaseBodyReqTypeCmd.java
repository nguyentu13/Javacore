package com.xtel.vngolf.api.controller.base;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.listener.request.BaseReq;
import com.xtel.vngolf.api.listener.response.BaseResp;
import com.xtel.vngolf.api.listener.response.EnumRsCode;

public abstract class AbsApiBaseBodyReqTypeCmd extends AbsApiBaseCmd {
	
	public AbsApiBaseBodyReqTypeCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
		super(httpServletRequest, jsonRequest, classRequest);
	}
	
	@Override
	public void execute() {
		
		logger.debug(String.format("transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s",
				transid, channel, logRequest(), jsonRequest, classRequest));
		
		try {
			objRequest = gson.fromJson(jsonRequest, classRequest);
			if (objRequest == null) {
				objResponse = new BaseResp(EnumRsCode.REQUEST_INVALID.getCode(), EnumRsCode.REQUEST_INVALID.getMessage());
				throw new Exception("Invalid or empty json request");
			}
			transid = ((BaseReq) objRequest).getTransId();
			channel = ((BaseReq) objRequest).getChannel();
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			createRespone();
			return;
		}
		
		logger.debug(String.format("BEGIN_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s",
				transid, channel, logRequest(), jsonRequest, classRequest));
		
		if(!validateToken()) {
			createRespone();
			return;
		}
		
		if(!validateData()) {
			if(objResponse == null) {
				objResponse = new BaseResp(EnumRsCode.REQUEST_INVALID.getCode(), EnumRsCode.REQUEST_INVALID.getMessage());
			}
			createRespone();
			logger.debug(
					String.format("END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, cmd: %s, time: %s, response: %s",
							transid, channel, logRequest(), jsonRequest, classRequest, this.toString(), 
							logTimeExecute(), 
							logResponse()));
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
				String.format("END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, cmd: %s, time: %s, response: %s",
						transid, channel, logRequest(), jsonRequest, classRequest, this.toString(), 
						logTimeExecute(), 
						logResponse()));
	}

}
