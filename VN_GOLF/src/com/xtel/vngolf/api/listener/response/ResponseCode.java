package com.xtel.vngolf.api.listener.response;

public class ResponseCode{

	protected int code;
	protected String message;
	
	public ResponseCode() {
		super();
	}
	
	public ResponseCode(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseInfo [code=" + code + ", message=" + message + "]";
	}
	
}
