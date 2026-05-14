package com.agromart.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.dao.ProductDAO;

@WebServlet("/adminProducts")
public class AdminProductsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		// ✅ Only admin
		if (session == null || session.getAttribute("role") == null || (int) session.getAttribute("role") != 1) {

			res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
			return;
		}

		ProductDAO dao = new ProductDAO();

		req.setAttribute("products", dao.getPendingProducts());

		req.getRequestDispatcher("jsp/adminProducts.jsp").forward(req, res);
	}
}
