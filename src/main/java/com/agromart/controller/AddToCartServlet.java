package com.agromart.controller;

import java.io.IOException;

import com.agromart.dao.CartDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        HttpSession session = req.getSession();
        int userId = ((com.agromart.model.User) session.getAttribute("user")).getId();

        // ✅ Use DAO (clean architecture)
        CartDAO dao = new CartDAO();
        dao.addToCart(userId, productId, quantity);

        res.sendRedirect("products");
    }
}