<%@ page import="java.util.*, com.agromart.model.Order" %>

<p>User: <%= session.getAttribute("user") %></p>  <!-- ✅ ADD HERE -->

<h2>My Orders</h2>

<%
List<Order> list = (List<Order>) request.getAttribute("orders");
%>

<% if(list != null && !list.isEmpty()) { %>

<table border="1" cellpadding="10">
<tr>
    <th>Order ID</th>
    <th>Total Amount</th>
    <th>Date</th>
    <th>Status</th>
</tr>

<% for(Order o : list) { %>
<tr>
    <td><%= o.getId() %></td>
    <td>₹ <%= o.getTotalAmount() %></td>
    <td><%= o.getOrderDate() %></td>
    <td><%= o.getStatusId() %></td>
</tr>
<% } %>

</table>

<% } else { %>

<p>No orders found ❌</p>

<% } %>