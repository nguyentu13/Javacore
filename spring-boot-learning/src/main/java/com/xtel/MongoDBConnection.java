package com.xtel;

public class MongoDBConnection extends DatabaseConnection {
	@Override
	public void connect() {
		System.out.println("Da ket noi vs MongoDB: " + getUrl());
	}
}
