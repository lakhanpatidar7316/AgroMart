package com.agromart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.agromart.model.User;
import com.agromart.util.DBUtil;

public class UserDAO {

    public boolean registerUser(User user) {
        boolean status = false;

        try {
            Connection con = DBUtil.getConnection();

            String sql = "INSERT INTO user(first_name, last_name, email, mobile_number, user_role_id) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getMobileNumber());
            ps.setInt(5, user.getUserRoleId());

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