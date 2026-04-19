package com.agromart.dao;

import java.sql.*;
import java.util.*;

import com.agromart.model.Product;
import com.agromart.util.DBUtil;

public class CartDAO {

	public void addToCart(int userId, int productId, int quantity) {

		String sql = "INSERT INTO cart_item(cart_id, product_id, quantity) VALUES (?, ?, ?)";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Product> getCartItems(int userId) {

		List<Product> list = new ArrayList<>();

		String sql = "SELECT p.id, p.name, p.price, ci.quantity " + "FROM cart_item ci "
				+ "JOIN product p ON ci.product_id = p.id " + "WHERE ci.cart_id = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product();

				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));

				// using description temporarily
				p.setDescription(String.valueOf(rs.getInt("quantity")));

				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
