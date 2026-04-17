package com.agromart.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.dao.LoginDAO;
import com.agromart.model.User;
import com.agromart.util.DBUtil;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
	
	
	    private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {

	        String email = req.getParameter("email");
	        String password = req.getParameter("password");

	        LoginDAO dao = new LoginDAO();
	        User user = dao.validateUser(email, password);

	        try (Connection con = DBUtil.getConnection()) {

	            if (user != null) {

	                HttpSession session = req.getSession();
	                session.setAttribute("user", user);
	                session.setAttribute("sessionId", session.getId());

	                // login_activity
	                String sql1 = "INSERT INTO login_activity(user_id, status) VALUES (?, ?)";
	                try (PreparedStatement ps1 = con.prepareStatement(sql1)) {
	                    ps1.setInt(1, user.getId());
	                    ps1.setString(2, "SUCCESS");
	                    ps1.executeUpdate();
	                }

	                // user_session
	                String sql2 = "INSERT INTO user_session(user_id, session_id) VALUES (?, ?)";
	                try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
	                    ps2.setInt(1, user.getId());
	                    ps2.setString(2, session.getId());
	                    ps2.executeUpdate();
	                }

	                res.sendRedirect(req.getContextPath() + "/jsp/home.jsp");

	            } else {

	                String sql3 = "INSERT INTO login_activity(user_id, status) VALUES (?, ?)";
	                try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
	                    ps3.setNull(1, java.sql.Types.INTEGER);
	                    ps3.setString(2, "FAILED");
	                    ps3.executeUpdate();
	                }

	                res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

