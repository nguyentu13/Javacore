package com.xtel.restful.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.xtel.restful.entity.Product;
import com.xtel.restful.logging.Log;

public class ProductModel {
	private Connector connector;
	private Connection conn;
	private Logger logger = new Log().getLogger(ProductModel.class);

	public ProductModel() {
		this.connector = new Connector();
		this.conn = connector.getConnection();
	}

	public List<Product> findAll(int index) {
		List<Product> products = new ArrayList<>();
		int start = index * 5;
		String sql = "select * from products limit ?,?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, 5);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
				products.add(product);

			}
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.debug(e);
			}
		}
		return products;
	}

	public Product findById(int id) {
		Product product = null;
		String sql = "SELECT * FROM products WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));

			}
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			try {
				this.conn.close();
			} catch (SQLException e) {
				logger.debug(e);
			}
		}

		return product;
	}

	public int save(Product product) {
		int id = 0;
		String sql1 = "INSERT INTO products(name,price) VALUES(?,?)";
		String sql2 = "SELECT id FROM products ORDER BY id DESC LIMIT 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.execute();

			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.debug(e);
			}
		}

		return id;
	}

	public int numberOfPage() {
		int numberPage = 0;
		String sql = "select COUNT(id) as count from products";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int totalRow = rs.getInt("count");
				numberPage = (int) Math.ceil((double) totalRow / 5);
			}
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.debug(e);
			}
		}

		return numberPage;
	}

}
