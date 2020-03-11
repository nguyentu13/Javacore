package com.xtel.vngolf.api.listener.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CmsPageInfo {
	private int id;
	private String page_name;
	private String page_url;
	private String page_icon;
	private String description;
	private int parent_id;
	private String define_key;
	private String menu_index;


}
