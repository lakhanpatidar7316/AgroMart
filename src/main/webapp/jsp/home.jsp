<a href="<%= request.getContextPath() %>/logout">Logout</a>

<%
Integer role = (Integer) session.getAttribute("role");
%>

<!-- COMMON -->
<a href="<%= request.getContextPath() %>/products">View Products</a>

<!-- CUSTOMER -->
<% if(role != null && role == 3) { %>
    <a href="<%= request.getContextPath() %>/cart">Cart</a>
    <a href="<%= request.getContextPath() %>/orders">My Orders</a>
<% } %>

<!-- SELLER -->
<% if(role != null && role == 2) { %>
    <a href="<%= request.getContextPath() %>/jsp/addProduct.jsp">Add Product</a>
    <a href="<%= request.getContextPath() %>/myProducts">My Products</a>
<% } %>

<!-- ADMIN -->
<% if(role != null && role == 1) { %>
    <a href="<%= request.getContextPath() %>/jsp/admin.jsp">Admin Panel</a>
<% } %>