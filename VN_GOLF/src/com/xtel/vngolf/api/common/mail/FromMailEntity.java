package com.xtel.vngolf.api.common.mail;

public class FromMailEntity {

	private String fromMail;
	private String username;
	private String password;
	
	public FromMailEntity() {
		
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "FromMailEntity [fromMail=" + fromMail + ", username="
				+ username + ", password=" + password + "]";
	}
}