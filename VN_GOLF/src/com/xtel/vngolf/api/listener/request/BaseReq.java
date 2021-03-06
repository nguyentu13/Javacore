package com.xtel.vngolf.api.listener.request;

public class BaseReq {

	protected String channel;
	protected String transid;
	
	public BaseReq() {
		super();
	}
	
	public BaseReq(String channel, String transid) {
		super();
		this.channel = channel;
		this.transid = transid;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTransId() {
		return transid;
	}

	public void setTransId(String transid) {
		this.transid = transid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseReq [channel=");
		builder.append(channel);
		builder.append(", transid=");
		builder.append(transid);
		builder.append("]");
		return builder.toString();
	}
	
}
