package com.jobconsultationmanagement.servlets;

import com.jobconsultationmanagement.DBConnectionUtil;
//import static com.jobconsultationmanagement.DatabaseUtil.getUsers;
import com.jobconsultationmanagement.model.User;
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
                // Your code to fetch data
            } else {
                System.out.println("Failed to establish database connection");
            }
            
            String sql = "SELECT * FROM users"; // Assuming "users" is the correct table name
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        String role = resultSet.getString("role");

                        User user = new User(id, username, password, role);
//                        System.out.println(users);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = getUsers(); // Fetch user data from the database
        request.setAttribute("users", users);
        System.out.println(users);
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
     //Method to fetch user data from the database
//    private List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//        // Database interaction logic (replace with actual code)
//        return users;
//    }
}