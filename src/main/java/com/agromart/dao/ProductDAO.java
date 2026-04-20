package com.agromart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.agromart.model.Product;
import com.agromart.util.DBUtil;

public class ProductDAO {

	public boolean addProduct(Product product) {

		boolean status = false;

		String sql = "INSERT INTO product(name, description, category_id, price) VALUES (?, ?, ?, ?)";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getCategoryId());
			ps.setDouble(4, product.getPrice());

			int row = ps.executeUpdate();

			if (row > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	public List<Product> getAllProducts() {

	    List<Product> list = new ArrayList<>();

	    String sql = "SELECT p.*, pi.image_url FROM product p " +
	             "LEFT JOIN product_images pi ON p.id = pi.product_id";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Product p = new Product();

	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setDescription(rs.getString("description"));
	            p.setPrice(rs.getDouble("price"));

	            p.setImagePath(rs.getString("image_url")); // add this in model

	            list.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	public List<Product> getProductsBySeller(int sellerId) {

	    List<Product> list = new ArrayList<>();

	    String sql = "SELECT * FROM product WHERE seller_id=?";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, sellerId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Product p = new Product();

	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setDescription(rs.getString("description"));
	            p.setPrice(rs.getDouble("price"));

	            list.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	public void deleteProduct(int productId) {

	    try (Connection con = DBUtil.getConnection()) {

	        // delete image first (optional but safe)
	        String sql1 = "DELETE FROM product_images WHERE product_id=?";
	        PreparedStatement ps1 = con.prepareStatement(sql1);
	        ps1.setInt(1, productId);
	        ps1.executeUpdate();

	        // delete product
	        String sql2 = "DELETE FROM product WHERE id=?";
	        PreparedStatement ps2 = con.prepareStatement(sql2);
	        ps2.setInt(1, productId);
	        ps2.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public Product getProductById(int id) {

	    Product p = null;

	    try (Connection con = DBUtil.getConnection()) {

	        String sql = "SELECT * FROM product WHERE id=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            p = new Product();
	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setDescription(rs.getString("description"));
	            p.setPrice(rs.getDouble("price"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return p;
	}
	public void updateProduct(int id, String name, String desc, double price) {

	    try (Connection con = DBUtil.getConnection()) {

	        String sql = "UPDATE product SET name=?, description=?, price=? WHERE id=?";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, name);
	        ps.setString(2, desc);
	        ps.setDouble(3, price);
	        ps.setInt(4, id);

	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
