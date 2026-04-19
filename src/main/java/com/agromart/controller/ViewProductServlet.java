package com.agromart.controller;

import java.io.IOException;
import java.util.List;

import com.agromart.dao.ProductDAO;
import com.agromart.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
	public class ViewProductServlet extends HttpServlet {

	    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {

	        ProductDAO dao = new ProductDAO();
	        List<Product> list = dao.getAllProducts();

	        req.setAttribute("products", list);
	        req.getRequestDispatcher("jsp/products.jsp").forward(req, res);
	    }
	}

