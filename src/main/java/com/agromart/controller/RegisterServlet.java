package com.agromart.controller;
import java.io.IOException;

import com.agromart.dao.UserDAO;
import com.agromart.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {

	        String fname = req.getParameter("firstName");
	        String lname = req.getParameter("lastName");
	        String email = req.getParameter("email");
	        long mobile = Long.parseLong(req.getParameter("mobile"));

	        User user = new User();
	        user.setFirstName(fname);
	        user.setLastName(lname);
	        user.setEmail(email);
	        user.setMobileNumber(mobile);
	        user.setUserRoleId(1);

	        UserDAO dao = new UserDAO();
	        boolean result = dao.registerUser(user);

	        if (result) {
	            res.sendRedirect("jsp/login.jsp");
	        } else {
	            res.sendRedirect("jsp/register.jsp");
	        }
	    }
	}

