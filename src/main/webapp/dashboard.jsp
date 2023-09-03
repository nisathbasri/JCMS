<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/dashboard-style.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="logout.jsp">LogOut</a>
    </nav>
    
    <div class="container mt-4">
        
        <%-- Admin Dashboard Section --%>
        <c:if test="${user.role == 'admin'}">
            <h2>Welcome, Admin ${user.username}! </h2>
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
            <!-- Job Seeker Dashboard Section -->
            <h2>Welcome, Job Seeker ${user.username}!</h2>

                    <!-- Table to display appointment details -->
        <h3>Your Appointments</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Consultant</th>
                    <th>Notes</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${appointments}">
                    <tr>
                        <td>${appointment.date}</td>
                        <td>${appointment.time}</td>
                        <td>${appointment.consultantName}</td>
                        <td>${appointment.notes}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Button to create a new appointment -->
        <a href="create-appointment.jsp" class="btn btn-primary">Create New Appointment</a>
        </c:if>
        
        <%-- Consultant Dashboard Section --%>
        <c:if test="${user.role == 'consultant'}">
            <h2>Welcome, Consultant ${user.username}!</h2>
            <p>Manage availability, view appointments, etc.</p>
        </c:if>
    </div>
</body>
</html>
