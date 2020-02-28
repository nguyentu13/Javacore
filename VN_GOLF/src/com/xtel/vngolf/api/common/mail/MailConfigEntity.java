package com.xtel.vngolf.api.common.mail;

import java.util.Arrays;

public class MailConfigEntity {

	private String Host;
	private int Port;
	private boolean ssl;
	private String protocol;
	private String fromPersonal;
	private FromMailEntity[] froms;

	public MailConfigEntity() {
		super();
	}

	public String getHost() {
		return Host;
	}

	public void setHost(String host) {
		Host = host;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getFromPersonal() {
		return fromPersonal;
	}

	public void setFromPersonal(String fromPersonal) {
		this.fromPersonal = fromPersonal;
	}

	public FromMailEntity[] getFroms() {
		return froms;
	}

	public void setFroms(FromMailEntity[] froms) {
		this.froms = froms;
	}

	@Override
	public String toString() {
		return "MailConfigEntity [Host=" + Host + ", Port=" + Port + ", ssl="
				+ ssl + ", protocol=" + protocol + ", fromPersonal="
				+ fromPersonal + ", froms=" + Arrays.toString(froms) + "]";
	}
}