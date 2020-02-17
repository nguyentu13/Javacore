package com.xtel.webservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.xtel.webservice.cache.CaffeineCache;
import com.xtel.webservice.config.DatabaseConfiguration;
import com.xtel.webservice.log.Log;
import com.xtel.webservie.entiy.Schedule;

public class Connector {
	private final DatabaseConfiguration configrator = DatabaseConfiguration.getInstance();

	private final String url = configrator.getUrl();
	private final String user = configrator.getUser();
	private final String password = configrator.getPassword();
	private CaffeineCache cache = CaffeineCache.getInstance();
	private PreparedStatement ps = null;
	private Logger logger = new Log().getLogger(Connector.class);

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.debug(e);
		}
		return connection;

	}

	public void insertSchedule(Schedule schedule) {
		Connection con = getConnection();
		String sql = "insert into patient(code,name,birthday,address,email,phone) values (?,?,?,?,?,?)";
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
			logger.debug(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.debug(e);
			}
		}
	}

	public Schedule findByCode(String code) {
		Connection con = getConnection();
		Schedule patient = null;
		String sql = "select * from patients where code = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, code);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				patient = new Schedule(rs.getInt("id"), rs.getString("code"), rs.getString("name"),
						rs.getString("birthday"), rs.getString("address"), rs.getString("email"),
						rs.getString("phone"));

//				int order_id = rs.getInt("id");

//				cache.setOrderCache(code,patient);
			}

		} catch (SQLException e) {
			logger.info(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.info(e);
			}
		}
		return patient;
	}
}
