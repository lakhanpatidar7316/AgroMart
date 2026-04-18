package com.agromart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
