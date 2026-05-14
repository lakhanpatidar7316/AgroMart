<%@ page import="java.util.*, com.agromart.model.Product" %>

<h2>Pending Products</h2>

<%
List<Product> list = (List<Product>) request.getAttribute("products");
%>

<table border="1" cellpadding="10">

<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Status</th>
    <th>Action</th>
</tr>

<% if(list != null && !list.isEmpty()) {
   for(Product p : list) { %>

<tr>

    <td><%= p.getId() %></td>
    <td><%= p.getName() %></td>
    <td>₹ <%= p.getPrice() %></td>
    <td><%= p.getStatus() %></td>

    <td>

        <form action="<%= request.getContextPath() %>/approveProduct" method="post">

            <input type="hidden" name="id" value="<%= p.getId() %>">

            <button style="
                background:green;
                color:white;
                border:none;
                padding:6px 10px;
                border-radius:5px;
            ">
                Approve
            </button>

        </form>

    </td>

</tr>

<% } 
} else { %>

<tr>
    <td colspan="5">No pending products ❌</td>
</tr>

<% } %>

</table>