package com.agromart.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.dao.LoginDAO;
import com.agromart.model.User;
import com.agromart.util.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginDAO dao = new LoginDAO();
        User user = dao.validateUser(email, password);

        try (Connection con = DBUtil.getConnection()) {

            if (user != null) {

                // ✅ SESSION CREATE
                HttpSession session = req.getSession();
                session.setAttribute("user", user); // VERY IMPORTANT
                session.setAttribute("role", user.getUserRoleId());

                // ✅ LOGIN SUCCESS LOG
                String sql1 = "INSERT INTO login_activity(user_id, status) VALUES (?, ?)";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setInt(1, user.getId());
                ps1.setString(2, "SUCCESS");
                ps1.executeUpdate();

                // ✅ SESSION TRACKING
                String sql2 = "INSERT INTO user_session(user_id, session_id) VALUES (?, ?)";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setInt(1, user.getId());
                ps2.setString(2, session.getId());
                ps2.executeUpdate();

                res.sendRedirect(req.getContextPath() + "/jsp/home.jsp");

            } else {

                // ❌ FAILED LOGIN
                String sql3 = "INSERT INTO login_activity(user_id, status) VALUES (?, ?)";
                PreparedStatement ps3 = con.prepareStatement(sql3);
                ps3.setNull(1, java.sql.Types.INTEGER);
                ps3.setString(2, "FAILED");
                ps3.executeUpdate();

                res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}