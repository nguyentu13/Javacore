package com.xtel.soapwebservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.xtel.soapwebservice.config.DatabaseConfiguration;
import com.xtel.soapwebservice.entity.Schedule;
import com.xtel.soapwebservice.log.Log;

public class Connector {
	private final DatabaseConfiguration configrator = DatabaseConfiguration.getInstance();

	private final String url = configrator.getUrl();
	private final String user = configrator.getUser();
	private final String password = configrator.getPassword();
	private PreparedStatement ps = null;
	private Logger logger = new Log().getLogger(Connector.class);

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return connection;

	}

	public void insertSchedule(Schedule schedule) {
		Connection con = getConnection();
		String sql = "insert into schedule(code,name,birthday,address,email,phone) values (?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, schedule.getName());
			ps.setString(3, schedule.getBirthday());
			ps.setString(4, schedule.getAddress());
			ps.setString(5, schedule.getEmail());
			ps.setString(6, schedule.getPhone());
			ps.execute();
		} catch (SQLException e) {
			logger.warn(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.warn(e);
			}
		}
	}

	public Schedule findByCode(String code) {
		code = "812c242f-d10e-415c-ae77-7f2e7818ffcd";
		Connection con = getConnection();
		Schedule schedule = null;
		String sql = "select * from schedule where code = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, code);

			ResultSet rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				schedule = new Schedule(rs.getInt("id"), rs.getString("code"), rs.getString("name"),
						rs.getString("birthday"), rs.getString("address"), rs.getString("email"),
						rs.getString("phone"));
			}
		} catch (SQLException e) {
			logger.warn(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.warn(e);
			}
		}
		return schedule;
	}
}
