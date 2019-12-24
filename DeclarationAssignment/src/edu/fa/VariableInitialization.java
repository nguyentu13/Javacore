package edu.fa;

public class VariableInitialization {
	private String databaseConnection;
	private final String IP_ADDRESS="127.0.0.1";
	public void checkVariables() {
		System.out.println(databaseConnection);
		System.out.println(IP_ADDRESS);
		int year=2017;
		System.out.println(year);
	}
}
