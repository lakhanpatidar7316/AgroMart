package com.agromart.controller;

import java.io.IOException;

import com.agromart.dao.ProductDAO;
import com.agromart.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/productDetails")
public class ProductDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        ProductDAO dao = new ProductDAO();

        Product p = dao.getProductById(id);

        req.setAttribute("product", p);

        req.getRequestDispatcher("jsp/productDetails.jsp")
           .forward(req, res);
    }
}