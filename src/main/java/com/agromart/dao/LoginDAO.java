package com.agromart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.agromart.model.User;
import com.agromart.util.DBUtil;

public class LoginDAO {

    public User validateUser(String email, String password) {

        User user = null;

        String sql = "SELECT * FROM user WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setId(rs.getInt("id"));                    // ✅ IMPORTANT
                user.setEmail(rs.getString("email"));
                user.setUserRoleId(rs.getInt("user_role_id"));  // ✅ IMPORTANT
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}