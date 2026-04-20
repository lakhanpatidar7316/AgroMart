package com.agromart.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.dao.ProductDAO;
import com.agromart.model.Product;
import com.agromart.model.User;

@WebServlet("/myProducts")
public class MyProductsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // ✅ seller check
        if (session == null || session.getAttribute("role") == null || (int)session.getAttribute("role") != 2) {
            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int sellerId = user.getId();

        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductsBySeller(sellerId);

        req.setAttribute("products", list);
        req.getRequestDispatcher("jsp/myProducts.jsp").forward(req, res);
    }
}