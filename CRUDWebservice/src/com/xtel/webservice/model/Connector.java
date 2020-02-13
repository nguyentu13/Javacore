package com.xtel.webservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

import com.xtel.webservie.entiy.Patient;

public class Connector {
	private final String url = "jdbc:mysql://localhost:3306/tu";
	private final String user = "root";
	private final String password = "12111993";
	private PreparedStatement ps = null;
	
	public Connection getConnection() throws Exception {
		Connection connection= DriverManager.getConnection(url, user, password);
		return connection;
		
	}
	public void insertCustomer(Patient patient) throws Exception {
		Connection con = getConnection();
		String sql = "insert into patient(code,name,birthday,address,email,phone) values (?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1 , UUID.randomUUID().toString());
		ps.setString(2, patient.getName());
		ps.setString(3, patient.getBirthday());
		ps.setString(4, patient.getAddress());
		ps.setString(5, patient.getEmail());
		ps.setString(6, patient.getPhone());
		ps.execute();
		
		con.close();
		
	}
}
