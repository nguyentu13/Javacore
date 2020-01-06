package edu.fa;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleJDBCConnection {
	public Connection getJDBCConnection() throws Exception {
		String url = null;
		if (instance == null || instance.trim().isEmpty()) {
			url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
		}
//		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, username, password);
	}
	// jdbc:Oracle:thin:@<hostname>:<port>:<sid>

	private final String serverName = "localhost";
	private final String dbName = "orcl2";
	private final String portNumber = "1521";
	private final String sid = "orcl2";
	private final String instance = "";// LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	private final String username = "hr";
	private final String password = "12111993";

//	public static void main(String[] args) throws Exception{
//		Connection conn = new OracleJDBCConnection().getJDBCConnection();
//		System.out.println("Connection successs!");
//	}
}
