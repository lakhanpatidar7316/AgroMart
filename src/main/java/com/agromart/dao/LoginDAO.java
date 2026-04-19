package com.agromart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.agromart.model.User;
import com.agromart.util.DBUtil;

public class LoginDAO {

	public User validateUser(String email, String password) {
		User user = null;

		try {
			Connection con = DBUtil.getConnection();

			String sql = "SELECT * FROM user WHERE email=? AND password=? AND is_active=1";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);
			

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserRoleId(rs.getInt("user_role_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
