package com.xtel;

public class MysqlConnection extends DatabaseConnection{
	@Override
	public void connect() {
		System.out.println("Da ket noi vs MySQL: " + getUrl());
	}
}
