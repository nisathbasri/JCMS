package com.jobconsultationmanagement.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.jobconsultationmanagement.model.User;
import com.jobconsultationmanagement.DBConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Establish a database connection
        DBConnectionUtil dbConnection = new DBConnectionUtil();
        Connection connection = dbConnection.getConnection();
        // Authenticate user
        User user = null;
        try {
            user = User.authenticate(connection, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //response.sendRedirect("/WEB-INF/dashboard.jsp");
            response.sendRedirect(request.getContextPath() + "/dashboard");
           // request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=invalid");
        }
        
        // Close the database connection
//            dbConnection.closeConnection(connection);
    }
}
