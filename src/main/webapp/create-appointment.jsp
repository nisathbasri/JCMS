<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Appointment</title>
    <!-- Include any necessary CSS or stylesheets here -->
</head>
<body>
    <h1>Create New Appointment</h1>
    
    <form action="schedule-appointment" method="post">
        <label for="consultant">Select Consultant:</label>
        <select name="consultant" id="consultant" required>
            <c:forEach var="consultant" items="${consultants}">
                <option value="${consultant.id}">${consultant.name}</option>
            </c:forEach>
        </select>

        <br>

        <label for="date">Select Date:</label>
        <input type="date" name="date" id="date" required>

        <br>

        <label for="time">Select Time:</label>
        <input type="time" name="time" id="time" required>

        <br>

        <label for="notes">Appointment Notes (optional):</label>
        <textarea name="notes" id="notes" rows="4"></textarea>

        <br>

        <button type="submit">Schedule Appointment</button>
    </form>

    <!-- Add a back button or link to go back to the dashboard -->
    <a href="dashboard.jsp">Back to Dashboard</a>

</body>
</html>

