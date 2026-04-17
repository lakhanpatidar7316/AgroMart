package com.agromart.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.util.DBUtil;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session != null) {

			Integer userId = ((com.agromart.model.User) session.getAttribute("user")).getId();
			String sessionId = (String) session.getAttribute("sessionId");

			try (Connection con = DBUtil.getConnection()) {

				// ✅ Update logout time
				String sql = "UPDATE user_session SET logout_time = NOW(), is_active = false WHERE session_id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, sessionId);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}

			// ❌ Destroy session
			session.invalidate();
		}

		res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
	}
}
