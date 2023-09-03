<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Appointment Success</title>
</head>
<body>
    <h1>Appointment Successfully Scheduled</h1>
    <button onclick="goToDashboard()">Back to Dashboard</button>
</body>

<script>
    function goToDashboard() {
        window.location.href = "dashboard"; // Replace "dashboard" with the actual URL of your dashboard page
    }
</script>

</html>

