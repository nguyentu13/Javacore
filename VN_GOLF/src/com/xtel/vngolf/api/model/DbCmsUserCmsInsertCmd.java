package com.xtel.vngolf.api.model;

import java.sql.Types;
import java.util.Date;

import com.tbv.utils.db.cmd.DbCallableCmd;

public class DbCmsUserCmsInsertCmd extends DbCallableCmd{
	private int user_id;
	private String user_name;
	private String password;
	private String full_name;
	private Date birthday;
	private String email;
	private String phone_int;
	private String mobile_int;
	private int sex;
	private String address;
	private String unit;
	private String create_by;

	public DbCmsUserCmsInsertCmd(String transid, String channel, String user_name, String password, String full_name,
			Date birthday, String email, String phone_int, String mobile_int, int sex, String address, String unit, String create_by) {
		super(transid, channel);
		this.user_name = user_name;
		this.password = password;
		this.full_name = full_name;
		this.birthday = birthday;
		this.email = email;
		this.phone_int = phone_int;
		this.mobile_int = mobile_int;
		this.sex = sex;
		this.address = address;
		this.unit = unit;
		this.create_by = create_by;

	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);
		
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("user_insert", 13);
		
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setString(idx++, user_name);
		cst.setString(idx++, password);
		cst.setString(idx++, full_name);
		cst.setDate(idx++, birthday == null ? null : new java.sql.Date(birthday.getTime()));
		cst.setString(idx++, email);
		cst.setString(idx++, phone_int);
		cst.setString(idx++, mobile_int);
		cst.setInt(idx++, sex);
		cst.setString(idx++, address);
		cst.setString(idx++, unit);
		cst.setString(idx++, create_by);
		
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "DbCmsUserCmsInsertCmd [user_name=" + user_name + ", password=" + password + ", full_name=" + full_name
				+ ", birthday=" + birthday + ", email=" + email + ", phone_int=" + phone_int + ", mobile_int="
				+ mobile_int + ", sex=" + sex + ", address=" + address + ", unit=" + unit + ", create_by=" + create_by + "]";
	}
}
