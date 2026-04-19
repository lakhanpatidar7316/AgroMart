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
}
