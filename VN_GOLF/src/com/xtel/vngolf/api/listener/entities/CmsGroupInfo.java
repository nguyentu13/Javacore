package com.xtel.vngolf.api.listener.entities;

import java.util.Date;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class CmsGroupInfo {

	private int id;
	private String group_name;
	private String description;
	private int status;
	private Date create_time;
	private String create_by;
	private Date update_time;
	private String update_by;

}
