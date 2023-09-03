package com.jobconsultationmanagement.servlets;

import com.jobconsultationmanagement.DBConnectionUtil;
import com.jobconsultationmanagement.model.User;
import com.jobconsultationmanagement.model.Consultant; 
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection()) {
            if (connection != null) {
                System.out.println("Database connection established successfully");
            } else {
                System.out.println("Failed to establish database connection");
            }
            
            String sql = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        String role = resultSet.getString("role");

                        User user = new User(id, username, password, role);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public static List<Consultant> getConsultants() {
        List<Consultant> consultants = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM consultants WHERE availability='active'"; // Adjust the SQL query according to your database schema
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String specialization = resultSet.getString("specialization");
                        String availability = resultSet.getString("availability");

                        Consultant consultant = new Consultant(id, name, specialization, availability);
                        consultants.add(consultant);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultants;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = getUsers(); // Fetch user data from the database
        List<Consultant> consultants = getConsultants(); // Fetch consultant data from the database

        request.setAttribute("users", users);
        request.setAttribute("consultants", consultants); // Set the list of consultants as an attribute

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
