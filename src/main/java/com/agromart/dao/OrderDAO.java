package com.agromart.dao;

import java.sql.*;
import java.util.*;

import com.agromart.model.Order;
import com.agromart.util.DBUtil;

public class OrderDAO {

    public List<Order> getOrdersByUser(int userId) {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM `order` WHERE user_id=? ORDER BY order_date DESC";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order();

                o.setId(rs.getInt("id"));
                o.setTotalAmount(rs.getDouble("total_amount"));
                o.setOrderDate(rs.getString("order_date"));
                o.setStatusId(rs.getInt("status_id"));

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
