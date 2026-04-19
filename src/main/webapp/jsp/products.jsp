<%@ page import="java.util.*, com.agromart.model.Product" %>

<h2>Product List</h2>

<%
List<Product> list = (List<Product>) request.getAttribute("products");
%>

<!-- 🔍 DEBUG -->
<p>List Size: <%= list == null ? "null" : list.size() %></p>

<div style="display:flex; flex-wrap:wrap;">

<% if(list != null && !list.isEmpty()) { %>

    <% for(Product p : list) { %>

    <div style="border:1px solid black; margin:10px; padding:10px; width:200px;">

        <img src="<%= request.getContextPath() %>/<%= p.getImagePath() %>" width="150"><br>

        <h3><%= p.getName() %></h3>
        <p>₹ <%= p.getPrice() %></p>

        <!-- ✅ FORM MUST BE INSIDE LOOP -->
        <form action="<%= request.getContextPath() %>/addToCart" method="post">
            <input type="hidden" name="productId" value="<%= p.getId() %>">
            <input type="number" name="quantity" value="1">
            <button type="submit">Add to Cart</button>
        </form>

    </div>

    <% } %>

<% } else { %>

    <p>No products available ❌</p>

<% } %>

</div>
