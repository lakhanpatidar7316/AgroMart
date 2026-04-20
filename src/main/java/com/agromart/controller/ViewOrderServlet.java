package com.agromart.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.agromart.dao.OrderDAO;
import com.agromart.model.Order;
import com.agromart.model.User;

@WebServlet("/orders")
public class ViewOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // ✅ LOGIN CHECK
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        OrderDAO dao = new OrderDAO();
        List<Order> list = dao.getOrdersByUser(userId);

        req.setAttribute("orders", list);
        req.getRequestDispatcher("jsp/orders.jsp").forward(req, res);
    }
}