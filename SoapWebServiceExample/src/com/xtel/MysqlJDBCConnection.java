package com.xtel;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class MysqlJDBCConnection {
	
	 
	    /**
	     * main
	     * 
	     * @author viettuts.vn
	     * @param args
	     * @throws Exception 
	     */
	    public static void main(String args[]) throws Exception {
	    	String path = new File("config/config.properties").getAbsolutePath();
			FileInputStream fis = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(fis);
			String db_url = prop.getProperty("DB_URL");
			String username = prop.getProperty("USER_NAME");
			String password = prop.getProperty("PASSWORD");
	        try {
	            // connnect to database 'testdb'
	            Connection conn = getConnection(db_url, username, password);
	            // crate statement
	            Statement stmt = (Statement) conn.createStatement();
	            // get data from table 'student'
	            ResultSet rs = stmt.executeQuery("select * from user");
	            // show data
	            while (rs.next()) {
	                System.out.println(rs.getInt(1) + "  " + rs.getString(2) 
	                        + "  " + rs.getString(3)+ "  " + rs.getString(4)+ "  " + rs.getString(5));
	            }
	            // close connection
	            conn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	 
	 
	    /**
	     * create connection 
	     * 
	     * @author viettuts.vn
	     * @param dbURL: database's url
	     * @param userName: username is used to login
	     * @param password: password is used to login
	     * @return connection
	     */
	    public static Connection getConnection(String dbURL, String userName, 
	            String password) {
	        Connection conn = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(dbURL, userName, password);
	            System.out.println("connect successfully!");
	        } catch (Exception ex) {
	            System.out.println("connect failure!");
	            ex.printStackTrace();
	        }
	        return conn;
	    }
	}

