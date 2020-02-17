package com.xtel.restful.authentication;

public class Authen {
	private static Authen ins;
	private Authen() {}
	public synchronized static Authen getInstance() {
		if(ins == null) {
			ins = new Authen();
		}
		
		return ins;
	}
	
	public boolean authentication(String author, String type) {
		if(author.equals(type)) {
			return true;
		} 
		return false;
	}
}
