package com.xtel.webservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.xtel.webservie.entiy.Customer;

public class Connector {
	private final String url = "jdbc:mysql://localhost:3306/tu";
	private final String user = "root";
	private final String password = "12111993";
	private PreparedStatement ps = null;
	
	public Connection getConnection() throws Exception {
		Connection connection= DriverManager.getConnection(url, user, password);
		return connection;
		
	}
	public void insertCustomer(Customer customer) throws Exception {
		Connection con = getConnection();
		String sql = "insert into customer(cusname) values (?)";
		ps = con.prepareStatement(sql);
		ps.setString(1 , customer.getName());
		ps.execute();
		con.close();
		
	}
}
