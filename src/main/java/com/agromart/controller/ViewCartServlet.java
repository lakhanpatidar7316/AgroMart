package com.agromart.controller;

import java.io.IOException;
import java.util.List;

import com.agromart.dao.CartDAO;
import com.agromart.dao.ProductDAO;
import com.agromart.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class ViewCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int userId = ((com.agromart.model.User) session.getAttribute("user")).getId();

		CartDAO dao = new CartDAO();
		List<Product> list = dao.getCartItems(userId);

		req.setAttribute("cartItems", list);
		req.getRequestDispatcher("jsp/cart.jsp").forward(req, res);
	}
}
