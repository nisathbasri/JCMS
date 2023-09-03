package com.jobconsultationmanagement.servlets;

import com.jobconsultationmanagement.DBConnectionUtil;
import com.jobconsultationmanagement.model.Appointment;
import com.jobconsultationmanagement.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/view-appointments")
public class ViewAppointmentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && "jobseeker".equals(user.getRole())) {
            // Retrieve appointment data for the current jobseeker from the database
            List<Appointment> appointments = getAppointmentsForJobseeker(user.getId());

            // Set appointments as an attribute for the JSP to access
            request.setAttribute("appointments", appointments);

            // Forward the request to the dashboard.jsp page
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            // Handle unauthorized access
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    // Method to retrieve appointments for a jobseeker from the database
    private List<Appointment> getAppointmentsForJobseeker(int jobseekerId) {
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection()) {
            String sql = "SELECT appointment_id, date, time, consultant_id, notes " +
                    "FROM appointments " +
                    "WHERE jobseeker_id = ?";
            System.out.println(sql);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, jobseekerId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int appointmentId = resultSet.getInt("appointment_id");                    
                    int consultantId = resultSet.getInt("consultant_id");
                    String date = resultSet.getString("date");
                    String time = resultSet.getString("time");
                    String notes = resultSet.getString("notes");

                    // Create an Appointment object and add it to the list
                    Appointment appointment = new Appointment(appointmentId, jobseekerId, consultantId, date, time, notes);
                    appointments.add(appointment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
