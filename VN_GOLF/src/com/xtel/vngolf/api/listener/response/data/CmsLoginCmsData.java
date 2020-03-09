package com.xtel.vngolf.api.listener.response.data;

import java.util.List;

import com.xtel.vngolf.api.listener.entities.CmsGroupInfo;
import com.xtel.vngolf.api.listener.entities.CmsPageInfo;
import com.xtel.vngolf.api.listener.entities.CmsUser;

public class CmsLoginCmsData {
	
	private String token;
	private CmsUser user_info;
	private CmsGroupInfo group_info;
	private List<CmsPageInfo> page_infos;

	public CmsLoginCmsData() {
		super();
	}
	
	public CmsLoginCmsData(String token, CmsUser user_info, CmsGroupInfo group_info, List<CmsPageInfo> page_infos) {
		super();
		this.token = token;
		this.user_info = user_info;
		this.group_info = group_info;
		this.page_infos = page_infos;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CmsUser getUser_info() {
		return user_info;
	}

	public void setUser_info(CmsUser user_info) {
		this.user_info = user_info;
	}

	public CmsGroupInfo getGroup_info() {
		return group_info;
	}

	public void setGroup_info(CmsGroupInfo group_info) {
		this.group_info = group_info;
	}

	public List<CmsPageInfo> getPage_infos() {
		return page_infos;
	}

	public void setPage_infos(List<CmsPageInfo> page_infos) {
		this.page_infos = page_infos;
	}

	@Override
	public String toString() {
		return "CmsLoginData [token=" + token + ", user_info=" + user_info + ", group_info=" + group_info
				+ ", page_infos=" + (page_infos == null ? "null" : String.valueOf(page_infos.size())) + "]";
	}

}
