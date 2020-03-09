package com.xtel.vngolf.api.listener.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CmsUserInfoReq extends BaseReq{
	private int user_id;
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
	private int status;
	private String list_group;
	private String create_by;
	private String update_by;
}
