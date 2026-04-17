<!DOCTYPE html>
<html>
<body>

<h2>Login</h2>

<form action="<%= request.getContextPath() %>/login" method="post">
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

</body>
</html>