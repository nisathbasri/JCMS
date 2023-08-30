<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Dashboard</a>
    </nav>
    
    <div class="container mt-4">
        <%-- Admin Dashboard Section --%>
        <c:if test="${user.role == 'admin'}">
            <h2>Welcome, Admin!</h2>
            <p>Manage users and other admin functionalities.</p>
            <h3>User List</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.username}</td>
                            <td>${u.role}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <%-- Job Seeker Dashboard Section --%>
        <c:if test="${user.role == 'jobseeker'}">
            <h2>Welcome, Job Seeker!</h2>
            <p>Schedule appointments, view appointments, etc.</p>
        </c:if>
        
        <%-- Consultant Dashboard Section --%>
        <c:if test="${user.role == 'consultant'}">
            <h2>Welcome, Consultant!</h2>
            <p>Manage availability, view appointments, etc.</p>
        </c:if>
    </div>
</body>
</html>
