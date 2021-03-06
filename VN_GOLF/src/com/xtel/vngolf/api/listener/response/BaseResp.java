package com.xtel.vngolf.api.listener.response;

public class BaseResp {

	protected ResponseCode error;
	protected Object data;

	public BaseResp() {
		super();
	}

	public BaseResp(int code, String message) {
		super();
		this.error = new ResponseCode(code, message);
	}

	public BaseResp(int code, String message, Object data) {
		super();
		this.error = new ResponseCode(code, message);
		this.data = data;
	}

	public ResponseCode getError() {
		return error;
	}

	public void setError(ResponseCode error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "BaseResp [error=" + error + ", data=" + data + "]";
	}

}
