package com.xtel.vngolf.api.controller.base;

import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tbv.utils.db.DBLogic;
import com.tbv.utils.db.cmd.DbCommand;
import com.tbv.utils.textbase.StringUtil;
import com.tbv.utils.textbase.StringUtils;
import com.tbv.utils.textbase.TimeUtils;
import com.xtel.vngolf.api.client.GHClient;
import com.xtel.vngolf.api.common.mail.MailValidation;
import com.xtel.vngolf.api.common.utils.AppUtils;
import com.xtel.vngolf.api.config.CoreConfig;
import com.xtel.vngolf.api.listener.entities.CmsUser;
import com.xtel.vngolf.api.listener.response.BaseResp;
import com.xtel.vngolf.api.listener.response.EnumRsCode;
import com.xtel.vngolf.api.listener.response.ResponseCode;
import com.xtel.vngolf.api.main.MainApplication;
import com.xtel.vngolf.api.sercurity.JWTUtil;

public abstract class AbsApiBaseCmd {

	protected final Log logger = LogFactory.getLog(this.getClass());

	protected static DBLogic dbCtrl = MainApplication.cmsDb.getDbCtrl();

	protected static Gson gson = new GsonBuilder().setDateFormat(CoreConfig.API_DATE_TIME_OUTPUT_FORMAT)
			.serializeNulls().create();

	protected HttpServletRequest httpServletRequest;
	protected GHClient ghClient = MainApplication.ghClient;

	protected String transid;
	protected String channel;
	protected String token;

	protected String jsonRequest;
	protected Class<?> classRequest;
	protected Object objRequest;
	protected CmsUser userCmsLogin;

	protected String strResponse = null;
	protected BaseResp objResponse;
	protected Response response;

	protected long begin_time;
	protected long end_time;

	public AbsApiBaseCmd(HttpServletRequest httpServletRequest, String channel, String transid) {
		super();
		this.begin_time = System.currentTimeMillis();
		this.httpServletRequest = httpServletRequest;
		this.token = getToken();
		this.channel = channel;
		this.transid = transid;
	}

	public AbsApiBaseCmd(HttpServletRequest httpServletRequest, String jsonRequest) {
		super();
		this.begin_time = System.currentTimeMillis();
		this.httpServletRequest = httpServletRequest;
		this.token = getToken();
		this.jsonRequest = jsonRequest;
	}

	public AbsApiBaseCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
		super();
		this.begin_time = System.currentTimeMillis();
		this.httpServletRequest = httpServletRequest;
		this.token = getToken();
		this.jsonRequest = jsonRequest;
		this.classRequest = classRequest;
	}

	protected String getToken() {

		if (httpServletRequest == null) {
			return null;
		}

		try {
			return httpServletRequest.getHeader("Token_key");
		} catch (Exception e) {
			return null;
		}
	}

	protected boolean validateToken() {

		JWTUtil jwtUtil = new JWTUtil();

		DecodedJWT decodedJWT = jwtUtil.decodeJWT(token);
		if (decodedJWT == null) {
			objResponse = new BaseResp(EnumRsCode.TOKEN_INVALID.getCode(), EnumRsCode.TOKEN_INVALID.getMessage());
			logger.debug(String.format(
					"END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, time: %s, token invalid",
					transid, channel, logRequest(), jsonRequest, classRequest, logTimeExecute()));
			return false;
		}

		Date expireDate = decodedJWT.getExpiresAt();
		if (new Date().after(expireDate)) {
			objResponse = new BaseResp(EnumRsCode.TOKEN_EXPIRE.getCode(), EnumRsCode.TOKEN_EXPIRE.getMessage());
			logger.debug(String.format(
					"END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, time: %s, token expire",
					transid, channel, logRequest(), jsonRequest, classRequest, logTimeExecute()));
			return false;
		}

		String strJsonData = jwtUtil.decodeJWT2(token);
		if (StringUtil.isNullOrEmpty(strJsonData)) {
			objResponse = new BaseResp(EnumRsCode.TOKEN_INVALID.getCode(), EnumRsCode.TOKEN_INVALID.getMessage());
			logger.debug(String.format(
					"END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, token expire, time exec: %s",
					transid, channel, logRequest(), jsonRequest, classRequest, logTimeExecute()));
			return false;
		}

		userCmsLogin = gson.fromJson(strJsonData, CmsUser.class);
		logger.debug(
				String.format("transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, accLogin: %s",
						transid, channel, logRequest(), jsonRequest, classRequest, userCmsLogin));

		if (userCmsLogin == null) {
			objResponse = new BaseResp(EnumRsCode.TOKEN_INVALID.getCode(), EnumRsCode.TOKEN_INVALID.getMessage());
			logger.debug(String.format(
					"END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, token expire, time exec: %s",
					transid, channel, logRequest(), jsonRequest, classRequest, logTimeExecute()));
			return false;
		}

		logger.debug(String.format(
				"transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, token pass validation",
				transid, channel, logRequest(), jsonRequest, classRequest));

		return true;
	}

	public void execute() {

	}

	protected boolean validateData() {
		return true;
	};

	protected abstract void executeCmd() throws Exception;

	protected void executeDbCmd(DbCommand dbCmd) {

		try {
			dbCtrl.executeCommand(dbCmd);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}

	protected void createRespone() {

		ResponseBuilder builder = null;

		if (objResponse == null) {
			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(), EnumRsCode.UNKNOWN_ERROR.getMessage());
		}
		strResponse = gson.toJson(objResponse);

		try {
			builder = Response.ok();
			builder.entity(strResponse);
		} catch (Exception e) {
			logger.warn(String.format(
					"END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, cmd: %s, time: %s, response: %s, Exception: %s",
					transid, channel, logRequest(), jsonRequest, classRequest, this.toString(), logTimeExecute(),
					logResponse(), e.getMessage()), e);
			builder = Response.status(Response.Status.SERVICE_UNAVAILABLE.getStatusCode());
			builder.entity(e.toString());
		}

		response = builder.build();
	}

	protected void createRespone(String output_format) {

		ResponseBuilder builder = null;

		if (objResponse == null) {
			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(), EnumRsCode.UNKNOWN_ERROR.getMessage());
		}
		gson = new GsonBuilder().setDateFormat(output_format)
				.serializeNulls().create();
		
		strResponse = gson.toJson(objResponse);

		try {
			builder = Response.ok();
			builder.entity(strResponse);
		} catch (Exception e) {
			logger.warn(String.format(
					"END_CMD transid: %s, channel: %s, request: %s, jsonRequest: %s, classRequest: %s, cmd: %s, time: %s, response: %s, Exception: %s",
					transid, channel, logRequest(), jsonRequest, classRequest, this.toString(), logTimeExecute(),
					logResponse(), e.getMessage()), e);
			builder = Response.status(Response.Status.SERVICE_UNAVAILABLE.getStatusCode());
			builder.entity(e.toString());
		}

		response = builder.build();
	}

	public Response getResponse() {
		return response;
	}

	public String logResponse() {
		return StringUtil.cutString(strResponse, CoreConfig.RESPONSE_LOG_MAX_LENGTH);
	}

	public String logRequest() {

		if (httpServletRequest == null)
			return null;

		return String.format("[from_addr: %s, method: %s, path: %s, param: %s, token: %s]",
				httpServletRequest.getRemoteAddr(), httpServletRequest.getMethod(), httpServletRequest.getPathInfo(),
				httpServletRequest.getQueryString(), StringUtil.cutString(token, CoreConfig.TOKEN_LOG_MAX_LENGTH));
	}

	public String logTimeExecute() {
		return TimeUtils.getDurations(System.currentTimeMillis(), begin_time);
	}

	public CmsUser getUserCmsLogin() {
		if (userCmsLogin == null) {
			return null;
		}
		return userCmsLogin;
	}
	
	public int getUserCmsLoginId() {
		if (userCmsLogin == null) {
			return 0;
		}
		return userCmsLogin.getUser_id();
	}

	public String getUserCmsLoginUserName() {
		if (userCmsLogin == null) {
			return null;
		}

		return userCmsLogin.getUser_name();
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [transid=" + transid + ", channel=" + channel + "]";
	}

	// truong nguyen
	protected void setResponse(int dbCode, String dbMsg, Object objData) {
		if (EnumRsCode.SUCCESS.getCode() == dbCode) {
			objResponse = new BaseResp(dbCode, dbMsg, objData);
			return;
		}
		objResponse = new BaseResp(dbCode, dbMsg);
	}

	protected void setResponse(int code, String msg) {
		objResponse = new BaseResp(code, msg);
	}

	protected void setResponse(ResponseCode codeResp , Object objData){
		if(EnumRsCode.SUCCESS.getCode() == codeResp.getCode()){
			objResponse = new BaseResp(codeResp.getCode(),codeResp.getMessage(),objData);
			return;
		}
		objResponse = new BaseResp(codeResp.getCode(),codeResp.getMessage());
	}


	protected boolean validatePhone(String phone){
		// validate phone
		if(!AppUtils.isValidMsisdnVn(phone)){
			setResponse(EnumRsCode.PHONE_INVALID.getCode(),EnumRsCode.PHONE_INVALID.getMessage());
			return false;
		}
		else{
			phone = AppUtils.formatMsisdn_84(phone);
		}

		if(!StringUtils.isNullOrEmpty(phone)){
			if(phone.length() <=10 || phone.length() >=12 || !Pattern.matches("[0-9+]+",phone)){
				return false;
			}
		}

		return true;
	}

	protected boolean validateEmail(String email){
		if(!MailValidation.isValidEmail(email)){
			setResponse(EnumRsCode.MAIL_INVALID.getCode(),EnumRsCode.MAIL_INVALID.getMessage());
			return false;
		}
		return true;
	}

	protected Date parseToDate(String birthDay){
		Date result = null;

		if(StringUtils.isNullOrEmpty(birthDay)){
			return null;
		}
		else{
			try{
				result = DateUtils.parseDate(birthDay, CoreConfig.API_DAY_INPUT_FORMAT);
			}
			catch (Exception ex){
				setResponse(EnumRsCode.UNKNOWN_ERROR.getCode(),EnumRsCode.UNKNOWN_ERROR.getMessage());
				return null;
			}
		}

		return result;
	}
}
