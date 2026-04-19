<%@ page import="java.util.*, com.agromart.model.Product" %>

<h2>Your Cart</h2>

<%
List<Product> list = (List<Product>) request.getAttribute("cartItems");
double total = 0;
%>

<% if(list != null && !list.isEmpty()) { %>

<table border="1" cellpadding="10">
<tr>
    <th>Name</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Action</th>
</tr>

<% for(Product p : list) { 
    int qty = Integer.parseInt(p.getDescription());
    double price = p.getPrice();
    total += qty * price;
%>

<tr>
    <td><%= p.getName() %></td>

    <td>₹ <%= price %></td>

    <!-- ✅ UPDATE QUANTITY -->
    <td>
        <form action="<%= request.getContextPath() %>/updateCart" method="post">
            <input type="hidden" name="productId" value="<%= p.getId() %>">
            <input type="number" name="quantity" value="<%= qty %>" min="1">
            <button type="submit">Update</button>
        </form>
    </td>

    <!-- ✅ REMOVE ITEM -->
    <td>
        <form action="<%= request.getContextPath() %>/removeFromCart" method="post">
            <input type="hidden" name="productId" value="<%= p.getId() %>">
            <button type="submit">Remove</button>
        </form>
    </td>

</tr>

<% } %>

</table>

<!-- ✅ TOTAL PRICE -->
<h3>Total: ₹ <%= total %></h3>

<% } else { %>

<p>Cart is empty ❌</p>

<% } %>