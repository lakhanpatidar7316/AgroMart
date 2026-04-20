package com.agromart.controller;

import java.io.IOException;

import com.agromart.dao.CartDAO;
import com.agromart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        CartDAO dao = new CartDAO();
        dao.updateQuantity(userId, productId, quantity);

        res.sendRedirect("cart");
    }
}
