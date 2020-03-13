package com.xtel.vngolf.api.listener.entities;

import java.util.Date;

public class CmsEvent {
	private String avatar_url;
	private String title;
	private String public_date;
	private int id;
	private String description;
	private String auName;
	public CmsEvent(String avatar_url, String title, String public_date, int id, String description, String auName) {
		this.avatar_url = avatar_url;
		this.title = title;
		this.public_date = public_date;
		this.id = id;
		this.description = description;
		this.auName = auName;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public String getTitle() {
		return title;
	}
	public String getPublic_date() {
		return public_date;
	}
	public int getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getAuName() {
		return auName;
	}
	
}
