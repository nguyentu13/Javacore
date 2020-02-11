package com.xtel.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jws.WebService;

@WebService(endpointInterface = "com.xtel.ws.OrderService")
public class OrderServiceImpl implements OrderService {

	public String[] getOrders() {
		return new String[]{"SSD", "Graphic Card", "GPU"};
	}

	public boolean addOrder(String order) {
		return true;
	}

	public int getUserCount() {
		int numusers = 0;
		String dbUrl = "jdbc:mysql://localhost/tu";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "Select count(*) FROM user";
		String userName = "root", password = "12111993";
		try {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection (dbUrl, userName, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			numusers = rs.getInt(1);
			} //end while
			con.close();
		} //end try

		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			return numusers;
		}
	}
	
}
