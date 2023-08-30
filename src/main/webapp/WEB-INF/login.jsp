<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login-style.css">
</head>
<body>
    <div class="login-container">
        <h2 class="login-title">Login</h2>
        <form action="login" method="post">
            <input type="text" class="login-input" name="username" placeholder="Username" required>
            <input type="password" class="login-input" name="password" placeholder="Password" required>
            <button type="submit" class="login-button">Login</button>
        </form>
    </div>
</body>
</html>
