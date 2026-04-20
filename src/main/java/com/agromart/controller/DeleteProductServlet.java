package com.agromart.controller;

import java.io.IOException;

import com.agromart.dao.ProductDAO;
import com.agromart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("productId"));

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("role") == null || (int)session.getAttribute("role") != 2) {
            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        ProductDAO dao = new ProductDAO();
        dao.deleteProduct(productId);

        res.sendRedirect("myProducts");
    }
}