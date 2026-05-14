package com.agromart.controller;

import java.io.IOException;
import java.util.List;

import com.agromart.dao.ProductDAO;
import com.agromart.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/products")
public class ViewProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String keyword = req.getParameter("keyword");
        String categoryStr = req.getParameter("categoryId");

        ProductDAO dao = new ProductDAO();

        List<Product> list;

        // ✅ SEARCH
        if(keyword != null && !keyword.trim().isEmpty()) {

            list = dao.searchProducts(keyword);

        }

        // ✅ FILTER
        else if(categoryStr != null && !categoryStr.isEmpty()) {

            int categoryId = Integer.parseInt(categoryStr);

            list = dao.getProductsByCategory(categoryId);

        }

        // ✅ ALL PRODUCTS
        else {

            list = dao.getAllProducts();
        }

        // ✅ IMPORTANT
        req.setAttribute("products", list);

        req.getRequestDispatcher("jsp/products.jsp")
           .forward(req, res);
    }
}