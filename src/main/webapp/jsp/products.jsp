<%@ page import="java.util.*, com.agromart.model.Product" %>

<h2 style="text-align:center;">Products</h2>

<!-- SEARCH -->
<form action="<%= request.getContextPath() %>/products" method="get">

    <input type="text"
           name="keyword"
           placeholder="Search products...">

    <button type="submit">Search</button>

</form>

<br>

<!-- CATEGORY FILTER -->
<form action="<%= request.getContextPath() %>/products" method="get">

    <select name="categoryId">

        <option value="">All Categories</option>

        <option value="1">Seeds</option>
        <option value="2">Fruits</option>
        <option value="3">Vegetables</option>
        <option value="4">Fertilizers</option>

    </select>

    <button type="submit">Filter</button>

</form>

<hr>

<%
List<Product> list = (List<Product>) request.getAttribute("products");
%>

<div style="display:flex; flex-wrap:wrap; justify-content:center;">

<% if(list != null && !list.isEmpty()) {
   for(Product p : list) { %>

<div style="
    border:1px solid #ddd;
    border-radius:10px;
    margin:15px;
    padding:15px;
    width:220px;
    text-align:center;
    box-shadow:0 2px 8px rgba(0,0,0,0.1);
">

    <!-- IMAGE -->
    <img src="<%= request.getContextPath() %>/<%= p.getImagePath() %>"
         width="180" height="150"
         style="border-radius:8px;"><br><br>

    <!-- NAME -->
    <h3><%= p.getName() %></h3>

    <!-- PRICE -->
    <p style="color:green; font-size:18px;">
        ₹ <%= p.getPrice() %>
    </p>

    <!-- ADD TO CART -->
    <form action="<%= request.getContextPath() %>/addToCart" method="post">

        <input type="hidden"
               name="productId"
               value="<%= p.getId() %>">

        <input type="hidden"
               name="quantity"
               value="1">

        <button style="
            background:#2874f0;
            color:white;
            border:none;
            padding:8px 15px;
            border-radius:5px;
            cursor:pointer;
        ">
            Add to Cart
        </button>

    </form>

</div>

<% } 
} else { %>

<p>No products available ❌</p>

<% } %>

</div>