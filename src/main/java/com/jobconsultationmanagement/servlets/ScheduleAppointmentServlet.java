package com.jobconsultationmanagement.servlets;

import com.jobconsultationmanagement.DBConnectionUtil;
import com.jobconsultationmanagement.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ScheduleAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && "jobseeker".equals(user.getRole())) {
            // Get appointment details from the form
            int consultantId = Integer.parseInt(request.getParameter("consultant")); // Parse the selected consultant ID
            String dateStr = request.getParameter("date"); // Adjust this parameter name
            String timeStr = request.getParameter("time"); // Adjust this parameter name
            String notes = request.getParameter("notes");
            System.out.println("dateStr: " + dateStr);
            System.out.println("timeStr: " + timeStr);

            try {
                // Save the appointment to the database using DBConnectionUtil (replace with your database logic)
                boolean appointmentSaved = saveAppointment(user.getId(), consultantId, dateStr, timeStr, notes);

                if (appointmentSaved) {
                    // Redirect to a success page or show a success message
                    response.sendRedirect("appointment-success.jsp");
                } else {
                    // Handle appointment save failure
                    response.sendRedirect("appointment-failure.jsp");
                    System.out.println("Wrong Handle");
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "Error: " + e.getMessage()); // Set an error message attribute
                // Handle invalid datetime format
                response.sendRedirect("appointment-failure.jsp");
                System.out.println("Wrong DateTimeFormat");
            }
        } else {
            // Handle unauthorized access
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    // Method to save the appointment in the database using DBConnectionUtil (replace with your database logic)
    private boolean saveAppointment(int jobseekerId, int consultantId, String dateStr, String timeStr, String notes) {
        try (Connection connection = DBConnectionUtil.getConnection()) {
            String sql = "INSERT INTO appointments (jobseeker_id, consultant_id, date, time, notes) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, jobseekerId);
                preparedStatement.setInt(2, consultantId);
                preparedStatement.setString(3, dateStr);
                preparedStatement.setString(4, timeStr);
                preparedStatement.setString(5, notes);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
