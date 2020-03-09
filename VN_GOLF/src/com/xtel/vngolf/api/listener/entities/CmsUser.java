package com.xtel.vngolf.api.listener.entities;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CmsUser {
	private int id;
	private String user_name;
	private String password;
	private String full_name;
	private Date birth_day;
	private String email;
	private String phone_number;
	private String mobile_number;
	private int gender;
	private String address;
	private String unit;
	private int status;
	private int is_admin;
	private Date create_time;
	private String create_by;
	private Date update_time;
	private String update_by;
}
