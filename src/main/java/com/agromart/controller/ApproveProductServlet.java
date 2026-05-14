package com.agromart.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.dao.ProductDAO;

@WebServlet("/approveProduct")
public class ApproveProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // ✅ admin check
        if (session == null ||
            session.getAttribute("role") == null ||
            (int) session.getAttribute("role") != 1) {

            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        ProductDAO dao = new ProductDAO();
        dao.approveProduct(id);

        res.sendRedirect("adminProducts");
    }
}