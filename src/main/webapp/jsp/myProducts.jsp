<%@ page import="java.util.*, com.agromart.model.Product" %>

<h2>My Products</h2>

<%
List<Product> list = (List<Product>) request.getAttribute("products");
%>

<div style="display:flex; flex-wrap:wrap;">

<% if(list != null && !list.isEmpty()) {
   for(Product p : list) { %>

<div style="border:1px solid black; margin:10px; padding:10px; width:200px;">

    <h3><%= p.getName() %></h3>
    <p>₹ <%= p.getPrice() %></p>

    <p>Status: Pending</p>
<form action="<%= request.getContextPath() %>/deleteProduct" method="post">
    <input type="hidden" name="productId" value="<%= p.getId() %>">
    <button style="background:red; color:white;">Delete</button>
</form>

<br>

<a href="<%= request.getContextPath() %>/editProduct?id=<%= p.getId() %>">
    <button>Edit</button>
</a>
</div>

<% } 
} else { %>

<p>No products found ❌</p>

<% } %>

</div>