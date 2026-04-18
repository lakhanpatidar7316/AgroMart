<!DOCTYPE html>
<html>
<body>

<h2>Add Product</h2>

<%
    HttpSession sessionObj = request.getSession(false);

    if (sessionObj == null || sessionObj.getAttribute("role") == null || (int)sessionObj.getAttribute("role") != 1) {
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        return;
    }
%>

<!-- ✅ MESSAGE DISPLAY -->
<% if(request.getParameter("success") != null) { %>
    <p style="color:green;">Product added successfully!</p>
<% } %>

<% if(request.getParameter("error") != null) { %>
    <p style="color:red;">Something went wrong!</p>
<% } %>

<% if("empty".equals(request.getParameter("error"))) { %>
    <p style="color:red;">All fields are required!</p>
<% } else if("number".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Invalid number format!</p>
<% } %>

<form action="<%= request.getContextPath() %>/addProduct" 
      method="post" 
      enctype="multipart/form-data">

    Name: <input type="text" name="name" required><br><br>
    Description: <input type="text" name="description" required><br><br>
    Category ID: <input type="number" name="categoryId" required><br><br>
    Price: <input type="text" name="price" required><br><br>

    Image: <input type="file" name="image" required><br><br>

    <button type="submit">Add Product</button>
</form>

</body>
</html>