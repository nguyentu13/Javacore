package com.xtel.vngolf.api.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tbv.utils.textbase.StringUtil;
import com.tbv.utils.textbase.StringUtils;
import com.xtel.vngolf.api.common.AppConstants;
import com.xtel.vngolf.api.listener.response.BaseResp;
import com.xtel.vngolf.api.listener.response.EnumRsCode;

public abstract class AbsApiBaseFwdBodyReqTypeCmd extends AbsApiBaseCmd {
	
	public AbsApiBaseFwdBodyReqTypeCmd(HttpServletRequest httpServletRequest, String jsonRequest) {
		super(httpServletRequest, jsonRequest);
	}
	
	@Override
	public void execute() {
		
		logger.debug(String.format("BEGIN_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s",
				transid, channel, logRequest(), jsonRequest));
		
		jsonRequest = StringUtils.null2Empty(jsonRequest);
		
		if(!validateToken()) {
			createRespone();
			return;
		}

		JsonParser parser = new JsonParser();
		JsonElement jsonElement = null;
		try {
			jsonElement = parser.parse(jsonRequest);
		} catch (Exception e) {
			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(), EnumRsCode.UNKNOWN_ERROR.getMessage());
			return;
		}
		
		JsonObject rootObject = jsonElement.getAsJsonObject();
		String channel = rootObject.get("channel").getAsString();
		if (StringUtil.isNullOrEmpty(channel)) {
			rootObject.addProperty("channel", AppConstants.CHANNEL_CMS_API);
			jsonRequest = rootObject.toString();
		}
		
//		JsonParser parser = new JsonParser();
//		JsonElement jsonElement = null;
//		try {
//			jsonElement = parser.parse(jsonRequest);
//		} catch (Exception e) {
//			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(),
//					EnumRsCode.UNKNOWN_ERROR.getMessage());
//			createRespone();
//			return;
//		}
//		JsonObject rootObject = jsonElement.getAsJsonObject();
//		if(rootObject.has("channel")) {
//			rootObject.addProperty("channel", AppConstants.CHANNEL_CMS_API);
//			jsonRequest = rootObject.toString();
//		}else {
//			String channel = rootObject.get("channel").getAsString();
//			if (StringUtil.isNullOrEmpty(channel)) {
//				rootObject.addProperty("channel", AppConstants.CHANNEL_CMS_API);
//				jsonRequest = rootObject.toString();
//			}
//		}
		
		if(!validateData()) {
			if(objResponse == null) {
				objResponse = new BaseResp(EnumRsCode.SUCCESS.getCode(), EnumRsCode.SUCCESS.getMessage());
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
	
	@Override
	protected void createRespone() {
		
		ResponseBuilder builder = null;
		
		if (StringUtil.isNullOrEmpty(strResponse)) {
			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(), EnumRsCode.UNKNOWN_ERROR.getMessage());
			strResponse = gson.toJson(objResponse);
		}
		
		try {
			builder = Response.ok();
			builder.entity(strResponse);
		} catch (Exception e) {
			logger.warn(
					String.format("END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, cmd: %s, time: %s, response: %s, Exception: %s",
							transid, channel, logRequest(), jsonRequest, classRequest, this.toString(), logTimeExecute(),
							logResponse(), e.getMessage()), e);
			builder = Response.status(Response.Status.SERVICE_UNAVAILABLE.getStatusCode());
			builder.entity(e.toString());
		}
		
		response = builder.build();
	}
	
	@Override
	protected boolean validateToken() {
		return true;
	}

}
