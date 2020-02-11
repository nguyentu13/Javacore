package com.xtel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService(endpointInterface = "com.xtel.UserService")
public class UserServiceImpl implements UserService {

	private static final Map<Integer, User> USERS = new HashMap<>();

	@Override
	public int insert(User user) {
		Integer id = generateUniqueId();
		user.setId(id);
		USERS.put(id, user);
		return id;
	}

	private int generateUniqueId() {
		return USERS.keySet().stream().max((x1, x2) -> x1 - x2).orElse(0) + 1;
	}

	@Override
	public boolean update(User user) {
		return USERS.put(user.getId(), user) != null;
	}

	@Override
	public boolean delete(int id) {
		return USERS.remove(id) != null;
	}

	@Override
	public User get(int id) {
		return USERS.getOrDefault(id, new User());
	}

	@Override
	public User[] getAll() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu", "root", "12111993");
		Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT * FROM user");
	    while(rs.next()) {
	    	USERS.put(rs.getInt(1), new User(rs.getInt(1), rs.getString(2)));
	    }
		return USERS.values().toArray(new User[0]);
	}
}
