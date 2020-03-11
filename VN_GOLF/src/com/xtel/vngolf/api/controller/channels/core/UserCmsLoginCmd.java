package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.tbv.utils.textbase.StringUtils;
import com.xtel.vngolf.api.listener.entities.CmsUser;
import com.xtel.vngolf.api.listener.request.LoginReq;
import com.xtel.vngolf.api.listener.response.BaseResp;
import com.xtel.vngolf.api.listener.response.EnumRsCode;
import com.xtel.vngolf.api.listener.response.data.CmsLoginCmsData;
import com.xtel.vngolf.api.model.DbCmsUserCmsLoginCmd;
import com.xtel.vngolf.api.sercurity.JWTUtil;
import com.xtel.vngolf.api.controller.base.AbsApiBaseBodyReqTypeCmd;

public class UserCmsLoginCmd extends AbsApiBaseBodyReqTypeCmd{

	public UserCmsLoginCmd(HttpServletRequest httpServletRequest, String jsonRequest, Class<?> classRequest) {
		super(httpServletRequest, jsonRequest, classRequest);
	}

	@Override
	protected void executeCmd() throws Exception {
		
		String username = ((LoginReq) objRequest).getUsername();
		String password = ((LoginReq) objRequest).getPassword();
		password = StringUtils.encodeMD5(password);

		DbCmsUserCmsLoginCmd dbCmd = new DbCmsUserCmsLoginCmd(transid, channel, username, password);
		executeDbCmd(dbCmd);

		if (EnumRsCode.SUCCESS.getCode() == dbCmd.getCode()) {
			CmsUser userObj = dbCmd.getObj();
			String jsonUserObj = gson.toJson(userObj);
			JWTUtil jwtObj = new JWTUtil();
			String token = jwtObj.encode(channel, jsonUserObj);
			objResponse = new BaseResp(dbCmd.getCode(), dbCmd.getMessage(),
					new CmsLoginCmsData(token, dbCmd.getObj(), dbCmd.getGroup_info(), dbCmd.getPage_infos()));
			
			return;
		}

		objResponse = new BaseResp(dbCmd.getCode(), dbCmd.getMessage());
		
	}
	
	@Override
	protected boolean validateData() {
		return true;
	};

	@Override
	protected boolean validateToken() {
		return true;
	}

}
