package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	// JDBC Driver Name And Database URL
	public static String JDBC_DRIVER = "Com.Mysql.Jdbc.Driver";
	public static String DB_URL = "Jdbc:mysql://Localhost/tu";

	// Database Credentials
	public static String DB_USER = "root";
	public static String DB_PASS = "12111993";

	// Database Connection
	Connection conn = null;

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("Connection success!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean isDisconnected() {
		boolean isDisconected = false;

		Connection conn = getConnection();
		String sql = "SELECT 1 FROM DUAL";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				isDisconected = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isDisconected;
	}
	

}
