<a href="<%= request.getContextPath() %>/logout">Logout</a>

<% if(session.getAttribute("role") != null && (int)session.getAttribute("role") == 1) { %>
    <a href="<%= request.getContextPath() %>/jsp/addProduct.jsp">Add Product</a>
<% } %>
<a href="<%= request.getContextPath() %>/products">View Products</a>
<a href="<%= request.getContextPath() %>/cart">View Cart</a>