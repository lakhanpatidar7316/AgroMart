
<%@ page import="com.agromart.model.Product" %>
<%
Product p = (Product) request.getAttribute("product");
%>

<div style="
    width:70%;
    margin:auto;
    padding:20px;
">

    <img src="<%= request.getContextPath() %>/<%= p.getImagePath() %>"
         width="350"
         height="300"
         style="border-radius:10px;">

    <h2><%= p.getName() %></h2>

    <h3 style="color:green;">
        ₹ <%= p.getPrice() %>
    </h3>

    <p>
        <%= p.getDescription() %>
    </p>

    <form action="<%= request.getContextPath() %>/addToCart"
          method="post">

        <input type="hidden"
               name="productId"
               value="<%= p.getId() %>">

        Quantity:
        <input type="number"
               name="quantity"
               value="1"
               min="1">

        <br><br>

        <button style="
            background:#2874f0;
            color:white;
            border:none;
            padding:10px 20px;
            border-radius:5px;
        ">
            Add to Cart
        </button>

    </form>

</div>