package com.agromart.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import com.agromart.model.User;
import com.agromart.util.DBUtil;

@MultipartConfig
@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // ✅ SESSION CHECK (SELLER ONLY)
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("role") == null || (int) session.getAttribute("role") != 2) {
            res.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int sellerId = user.getId();

        // 🔹 Get parameters
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String catStr = req.getParameter("categoryId");
        String priceStr = req.getParameter("price");

        Part filePart = req.getPart("image");
        String fileName = filePart.getSubmittedFileName();

        // 🔹 Validation
        if (name == null || name.trim().isEmpty() ||
            description == null || description.trim().isEmpty() ||
            catStr == null || catStr.trim().isEmpty() ||
            priceStr == null || priceStr.trim().isEmpty() ||
            fileName == null || fileName.isEmpty()) {

            res.sendRedirect(req.getContextPath() + "/jsp/addProduct.jsp?error=empty");
            return;
        }

        try {
            int categoryId = Integer.parseInt(catStr);
            double price = Double.parseDouble(priceStr);

            // 🔹 Save image
            String uploadPath = getServletContext().getRealPath("") + "images";

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            filePart.write(uploadPath + File.separator + fileName);

            String imagePath = "images/" + fileName;

            try (Connection con = DBUtil.getConnection()) {

                // 🔹 Insert product (WITH seller + status)
                String sql = "INSERT INTO product(name, description, category_id, price, seller_id, status) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, name);
                ps.setString(2, description);
                ps.setInt(3, categoryId);
                ps.setDouble(4, price);
                ps.setInt(5, sellerId);     // ✅ NEW
                ps.setString(6, "PENDING"); // ✅ NEW

                ps.executeUpdate();

                // 🔹 Get product ID
                ResultSet rs = ps.getGeneratedKeys();
                int productId = 0;

                if (rs.next()) {
                    productId = rs.getInt(1);
                }

                // 🔹 Insert image
                String sql2 = "INSERT INTO product_images(product_id, image_url) VALUES (?, ?)";
                PreparedStatement ps2 = con.prepareStatement(sql2);

                ps2.setInt(1, productId);
                ps2.setString(2, imagePath);
                ps2.executeUpdate();

                // 🔹 SUCCESS
                res.sendRedirect(req.getContextPath() + "/jsp/addProduct.jsp?success=1");
            }

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/jsp/addProduct.jsp?error=number");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect(req.getContextPath() + "/jsp/addProduct.jsp?error=1");
        }
    }
}