<a href="<%= request.getContextPath() %>/logout">Logout</a>

<% if(session.getAttribute("role") != null && (int)session.getAttribute("role") == 1) { %>
    <a href="<%= request.getContextPath() %>/jsp/addProduct.jsp">Add Product</a>
<% } %>