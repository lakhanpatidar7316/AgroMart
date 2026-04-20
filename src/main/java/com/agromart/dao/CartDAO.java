package com.agromart.dao;

import java.sql.*;
import java.util.*;

import com.agromart.model.Product;
import com.agromart.util.DBUtil;

public class CartDAO {

	public void addToCart(int userId, int productId, int quantity) {

	    try (Connection con = DBUtil.getConnection()) {

	        // 🔹 Step 1: Get cart_id from cart table
	        String getCart = "SELECT id FROM cart WHERE user_id=?";
	        PreparedStatement ps1 = con.prepareStatement(getCart);
	        ps1.setInt(1, userId);

	        ResultSet rs = ps1.executeQuery();

	        int cartId = 0;

	        if (rs.next()) {
	            cartId = rs.getInt("id");
	        } else {
	            // 🔹 If cart not exists → create
	            String createCart = "INSERT INTO cart(user_id) VALUES(?)";
	            PreparedStatement ps2 = con.prepareStatement(createCart, Statement.RETURN_GENERATED_KEYS);
	            ps2.setInt(1, userId);
	            ps2.executeUpdate();

	            ResultSet rs2 = ps2.getGeneratedKeys();
	            if (rs2.next()) {
	                cartId = rs2.getInt(1);
	            }
	        }

	        // 🔹 Step 2: Insert into cart_item
	        String sql = "INSERT INTO cart_item(cart_id, product_id, quantity) VALUES (?, ?, ?)";
	        PreparedStatement ps3 = con.prepareStatement(sql);

	        ps3.setInt(1, cartId);   // ✅ CORRECT
	        ps3.setInt(2, productId);
	        ps3.setInt(3, quantity);

	        ps3.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public List<Product> getCartItems(int userId) {

	    List<Product> list = new ArrayList<>();

	    String sql = "SELECT p.id, p.name, p.price, ci.quantity " +
	                 "FROM cart_item ci " +
	                 "JOIN cart c ON ci.cart_id = c.id " +
	                 "JOIN product p ON ci.product_id = p.id " +
	                 "WHERE c.user_id = ?";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Product p = new Product();

	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setPrice(rs.getDouble("price"));
	            p.setDescription(String.valueOf(rs.getInt("quantity")));

	            list.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	public void removeFromCart(int userId, int productId) {

	    try (Connection con = DBUtil.getConnection()) {

	        // 🔹 Get cart_id from cart table
	        String getCart = "SELECT id FROM cart WHERE user_id=?";
	        PreparedStatement ps1 = con.prepareStatement(getCart);
	        ps1.setInt(1, userId);

	        ResultSet rs = ps1.executeQuery();

	        int cartId = 0;

	        if (rs.next()) {
	            cartId = rs.getInt("id");
	        }

	        // 🔹 Delete item
	        String sql = "DELETE FROM cart_item WHERE cart_id=? AND product_id=?";
	        PreparedStatement ps2 = con.prepareStatement(sql);

	        ps2.setInt(1, cartId);
	        ps2.setInt(2, productId);

	        ps2.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void updateQuantity(int userId, int productId, int quantity) {

	    try (Connection con = DBUtil.getConnection()) {

	        // 🔹 Get cart_id from cart table
	        String getCart = "SELECT id FROM cart WHERE user_id=?";
	        PreparedStatement ps1 = con.prepareStatement(getCart);
	        ps1.setInt(1, userId);

	        ResultSet rs = ps1.executeQuery();

	        int cartId = 0;

	        if (rs.next()) {
	            cartId = rs.getInt("id");
	        }

	        // 🔹 Update quantity
	        String sql = "UPDATE cart_item SET quantity=? WHERE cart_id=? AND product_id=?";
	        PreparedStatement ps2 = con.prepareStatement(sql);

	        ps2.setInt(1, quantity);
	        ps2.setInt(2, cartId);
	        ps2.setInt(3, productId);

	        ps2.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
