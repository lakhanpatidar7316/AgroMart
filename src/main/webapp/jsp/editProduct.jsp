<form action="<%= request.getContextPath() %>/updateProduct" method="post">

    <input type="hidden" name="id" value="<%= request.getParameter("id") %>">

    Name: <input type="text" name="name"><br><br>
    Description: <input type="text" name="description"><br><br>
    Price: <input type="text" name="price"><br><br>

    <button type="submit">Update</button>
</form>