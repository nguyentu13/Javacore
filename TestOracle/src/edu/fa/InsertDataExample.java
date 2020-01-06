package edu.fa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class InsertDataExample {
	public static void main(String[] args) throws Exception {

		Connection conn = new OracleJDBCConnection().getJDBCConnection();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap so sinh vien: ");
		int n = Integer.parseInt(sc.nextLine());
		String name = "";
		for (int i = 0; i < n; i++) {
			
			while(true) {
				System.out.println("Nhap ten: ");
				 name = sc.nextLine();
				if(isExistName(name)) {
					
					System.out.println("Nhap lai ten");
				} else {
					break;
				}
			}
			System.out.println("Nhap gioi tinh: ");
			String gender = sc.nextLine();
			System.out.println("Nhap que quan: ");
			String country = sc.nextLine();
			System.out.println("Nhap tuoi: ");
			int age = Integer.parseInt(sc.nextLine());

			String sql = "Insert into STUDENT(NAME,GENDER,COUNTRY,AGE) values (?,?,?,?)  ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, country);
			ps.setInt(4, age);
			ps.execute();
		}

//		String sql="Update REGIONS Set REGION_NAME='bbbbbbb' WHERE REGION_ID=5";
//		String sql="Delete From REGIONS where REGION_ID=5";
//		statement.executeUpdate(sql);

//		System.out.println("Row affected = "+rowConut);
		System.out.println("Success!");
//		conn.close();

	}

	public static boolean isExistName(String name) throws Exception {
		Connection conn = new OracleJDBCConnection().getJDBCConnection();
		String sql = "Select Name from student";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> names = new ArrayList<>();
		while (rs.next()) {
			names.add(rs.getString(1));
		}
		System.out.println(names);
		for (String n : names) {
			if(n!=null) {
				if (n.equals(name)) {
					return true;
				}
			}
			
		}
		conn.close();
		return false;
	}
}
