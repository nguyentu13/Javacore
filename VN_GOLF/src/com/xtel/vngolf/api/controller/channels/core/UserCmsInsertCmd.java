package com.xtel.vngolf.api.controller.channels.core;



import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;

import com.tbv.utils.textbase.StringUtil;
import com.tbv.utils.textbase.StringUtils;
import com.xtel.vngolf.api.common.mail.MailValidation;
import com.xtel.vngolf.api.common.utils.AppUtils;
import com.xtel.vngolf.api.config.CoreConfig;
import com.xtel.vngolf.api.controller.base.AbsApiBaseBodyReqTypeCmd;
import com.xtel.vngolf.api.listener.request.CmsUserInfoReq;
import com.xtel.vngolf.api.listener.response.BaseResp;
import com.xtel.vngolf.api.listener.response.EnumRsCode;
import com.xtel.vngolf.api.model.DbCmsUserCmsInsertCmd;

public class UserCmsInsertCmd extends AbsApiBaseBodyReqTypeCmd {

	private Date d_birthday;
	private String user_name;
	private String password;
	private String full_name;
	private String birthday;
	private String email;
	private String phone_int;
	private String mobile_int;
	private int sex;
	private String address;
	private String unit;
	private String create_by;

	public UserCmsInsertCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
		super(httpServletRequest, jsonRequest, classRequest);
	}

	@Override
	protected void executeCmd() throws Exception {
		password = StringUtils.encodeMD5(password);

		DbCmsUserCmsInsertCmd dbCmd = new DbCmsUserCmsInsertCmd(transid, channel, user_name, password, full_name,
				d_birthday, email, phone_int, mobile_int, sex, address, unit, create_by);

		executeDbCmd(dbCmd);

		objResponse = new BaseResp(dbCmd.getCode(), dbCmd.getMessage(), dbCmd.getUser_id());
	}

	@Override
	protected boolean validateData() {
		user_name = ((CmsUserInfoReq) objRequest).getUser_name();
		password = ((CmsUserInfoReq) objRequest).getPassword();
		full_name = ((CmsUserInfoReq) objRequest).getFull_name();
		birthday = ((CmsUserInfoReq) objRequest).getBirthday();
		email = ((CmsUserInfoReq) objRequest).getEmail();
		phone_int = ((CmsUserInfoReq) objRequest).getPhone_int();
		mobile_int = ((CmsUserInfoReq) objRequest).getMobile_int();
		sex = ((CmsUserInfoReq) objRequest).getSex();
		address = ((CmsUserInfoReq) objRequest).getAddress();
		unit = ((CmsUserInfoReq) objRequest).getUnit();
		create_by = getUserCmsLoginUserName();

		if (StringUtils.isNullOrEmpty(user_name) || StringUtils.isNullOrEmpty(password)
				|| StringUtils.isNullOrEmpty(full_name) || StringUtils.isNullOrEmpty(address)
				|| StringUtil.isNullOrEmpty(mobile_int) || StringUtils.isNullOrEmpty(unit)
				|| StringUtils.isNullOrEmpty(create_by) || sex <= 0 || sex > 3) {
			objResponse = new BaseResp(EnumRsCode.REQUEST_INVALID.getCode(), EnumRsCode.REQUEST_INVALID.getMessage());
			return false;
		}

		if (!StringUtil.isNullOrEmpty(mobile_int) && !AppUtils.isValidMsisdnVn(mobile_int)) {
			objResponse = new BaseResp(EnumRsCode.PHONE_INVALID.getCode(), EnumRsCode.PHONE_INVALID.getMessage());
			return false;
		} else {
			mobile_int = AppUtils.formatMsisdn_84(mobile_int);
		}

		if (!StringUtils.isNullOrEmpty(phone_int)) {
			if (!Pattern.matches("[0-9+]+", phone_int)) {
				objResponse = new BaseResp(EnumRsCode.REQUEST_INVALID.getCode(),
						EnumRsCode.REQUEST_INVALID.getMessage());
				return false;
			}
		}

		if (StringUtil.isNullOrEmpty(email) && !MailValidation.isValidEmail(email)) {
			objResponse = new BaseResp(EnumRsCode.MAIL_INVALID.getCode(), EnumRsCode.MAIL_INVALID.getMessage());
			return false;
		}

		try {
			if (StringUtils.isNullOrEmpty(birthday)) {
				d_birthday = null;
			} else {
				d_birthday = DateUtils.parseDate(birthday, CoreConfig.API_DATE_TIME_INPUT_FORMAT);
			}
		} catch (Exception e) {
			objResponse = new BaseResp(EnumRsCode.UNKNOWN_ERROR.getCode(), EnumRsCode.UNKNOWN_ERROR.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", [d_birthday=" + d_birthday + ", user_name=" + user_name + ", password=" + password
				+ ", full_name=" + full_name + ", birthday=" + birthday + ", email=" + email + ", phone_int="
				+ phone_int + ", mobile_int=" + mobile_int + ", sex=" + sex + ", address=" + address + ", unit=" + unit
				+", create_by=" + create_by + "]";
	}

	@Override
	protected boolean validateToken() {
		return true;
	}
}
