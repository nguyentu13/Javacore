package com.xtel.vngolf.api.listener.response;

public enum EnumRsCode {
	
	SUCCESS(0, "SUCCESS"),
	ERROR(-1, "SYSTEM ERROR"),
	TOKEN_INVALID(1, "TOKEN INVALID"), 
	TOKEN_EXPIRE(2, "TOKEN EXPIRE"),
	REQUEST_INVALID(3, "REQUEST INVALID"),
	SYSTEM_BUSY(4, "SYSTEM BUSY"),
	UNKNOWN_ERROR(5, "UNKNOWN ERROR"),
	PHONE_INVALID(6, "PHONE INVALID"),
	MAIL_INVALID(7, "MAIL INVALID");
	
	int code;
	String message;
	
	EnumRsCode(int code, String msg) {
		this.code = code;
		this.message = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
