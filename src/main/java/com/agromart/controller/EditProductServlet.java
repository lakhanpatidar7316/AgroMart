package com.agromart.controller;

import java.io.IOException;

import com.agromart.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        ProductDAO dao = new ProductDAO();
        Product p = dao.getProductById(id); // we create this

        req.setAttribute("product", p);
        req.getRequestDispatcher("jsp/editProduct.jsp").forward(req, res);
    }
}