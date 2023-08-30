package com.jobconsultationmanagement;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.jobconsultationmanagement.model.User;

public class DatabaseUtil {

//    public static List<User> getUsers() {
//    List<User> users = new ArrayList<>();
//    users.add(new User(1, "mockUser1", "mockPassword1", "userRole"));
//    users.add(new User(2, "mockUser2", "mockPassword2", "userRole"));
//    return users;
//}

//    public static List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//
//        try (Connection connection = DBConnectionUtil.getConnection()) {
//            if (connection != null) {
//                System.out.println("Database connection established successfully");
//                // Your code to fetch data
//            } else {
//                System.out.println("Failed to establish database connection");
//            }
//            
//            String sql = "SELECT * FROM users"; // Assuming "users" is the correct table name
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        String username = resultSet.getString("username");
//                        String password = resultSet.getString("password");
//                        String role = resultSet.getString("role");
//
//                        User user = new User(id, username, password, role);
////                        System.out.println(users);
//                        users.add(user);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return users;
//    }
}
