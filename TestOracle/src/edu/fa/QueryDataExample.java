package edu.fa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryDataExample {
	public static void main(String[] args) throws Exception {
		//lay ra doi tuong ket noi vao database
		Connection conn= new OracleJDBCConnection().getJDBCConnection();
		//tao doi tuong statement
		Statement statement = conn.createStatement();
		
		String sql="Select * from countries";
		
		//thuc thi cau lenh SQL tra ve doi tuong Resultset
		ResultSet rs= statement.executeQuery(sql);
		//Duyet tren ket qua tra ve
		while(rs.next()) {
			String countryID= rs.getString(1);
			String countryName= rs.getString(2);
			int regionID= rs.getInt(3);
			
			System.out.println("COUNTRY_ID: "+countryID);
			System.out.println("COUNTRYNAME: "+countryName);
			System.out.println("REGION_ID: "+ regionID);
			System.out.println("-----------------");
		}
		conn.close();
	}
}
