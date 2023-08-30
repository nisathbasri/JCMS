package com.jobconsultationmanagement.servlets;

import com.jobconsultationmanagement.DBConnectionUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import com.jobconsultationmanagement.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

//@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        // Establish a database connection
        DBConnectionUtil dbConnection = new DBConnectionUtil();
        Connection connection = dbConnection.getConnection();
        // Register new user
        User newUser = new User(0, username, password, role);
        try {
            if (newUser.register(connection)) {
                HttpSession session = request.getSession();
                session.setAttribute("role", role); 
                
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                response.sendRedirect(request.getContextPath() + "/registration.jsp?error=failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
