package com.xtel;

public abstract class DatabaseConnection {
	private String url;
	
	public abstract void connect();
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
}
