package com.xtel.vngolf.api.model;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tbv.utils.db.cmd.DbCallableCmd;
import com.xtel.vngolf.api.listener.entities.CmsGroupInfo;
import com.xtel.vngolf.api.listener.entities.CmsPageInfo;
import com.xtel.vngolf.api.listener.entities.CmsUser;

public class DbCmsUserCmsLoginCmd extends DbCallableCmd {

	private final String username;
	private final String password;
	private CmsUser obj;
	private CmsGroupInfo group_info;
	private List<CmsPageInfo> page_infos = new ArrayList<CmsPageInfo>();

	public DbCmsUserCmsLoginCmd(String transid, String channel, String username, String password) {
		super(transid, channel);
		this.username = username;
		this.password = password;
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);

		ResultSet rs = cst.getResultSet();
		try {
			if (rs != null) {
				while (rs.next()) {
					obj = new CmsUser();
					obj.setId(rs.getInt("id"));
					obj.setUser_name(rs.getString("user_name"));
					obj.setPassword(rs.getString("password"));
					obj.setFull_name(rs.getString("full_name"));
					obj.setBirth_day(rs.getDate("birth_day"));
					obj.setEmail(rs.getString("email"));
					obj.setPhone_number(rs.getString("phone_number"));
					obj.setMobile_number(rs.getString("mobile_number"));
					obj.setGender(rs.getInt("gender"));
					obj.setAddress(rs.getString("address"));
					obj.setUnit(rs.getString("unit"));
					obj.setStatus(rs.getInt("status"));
					obj.setIs_admin(rs.getInt("is_admin"));
					obj.setCreate_time(rs.getDate("create_time"));
					obj.setCreate_by(rs.getString("create_by"));
					obj.setUpdate_time(rs.getDate("update_time"));
					obj.setUpdate_by(rs.getString("update_by"));
					break;
				}

			}
		} finally {
			rs.close();
		}
		cst.getMoreResults();
		ResultSet rs1 = cst.getResultSet();
		try {
			if (rs1 != null) {
				while (rs1.next()) {
					group_info = new CmsGroupInfo();
					group_info.setId(rs1.getInt("id"));
					group_info.setGroup_name(rs1.getString("group_name"));
					group_info.setDescription(rs1.getString("description"));
					group_info.setStatus(rs1.getInt("status"));
					group_info.setCreate_time(rs1.getDate("create_time"));
					group_info.setCreate_by(rs1.getString("create_by"));
					group_info.setUpdate_time(rs1.getDate("update_time"));
					group_info.setUpdate_by(rs1.getString("update_by"));
					break;
				}

			}
		} finally {
			rs.close();
		}
		
		cst.getMoreResults();
		ResultSet rs2 = cst.getResultSet();
		try {
			if (rs2 != null) {
				while (rs2.next()) {
					CmsPageInfo pageInfo = new CmsPageInfo();
					pageInfo.setId(rs2.getInt("id"));
					pageInfo.setPage_name(rs2.getString("page_name"));
					pageInfo.setPage_url(rs2.getString("page_url"));
					pageInfo.setPage_icon(rs2.getString("page_icon"));
					pageInfo.setDescription(rs2.getString("description"));
					pageInfo.setParent_id(rs2.getInt("parent_id"));
					pageInfo.setDefine_key(rs2.getString("define_key"));
					pageInfo.setMenu_index(rs2.getString("menu_index"));
					page_infos.add(pageInfo);
				}

			}
		} finally {
			rs.close();
		}
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("user_login", 4);

	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);

		cst.setString(idx++, username);
		cst.setString(idx++, password);

	}

	public CmsUser getObj() {
		return obj;
	}

	public void setObj(CmsUser obj) {
		this.obj = obj;
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
