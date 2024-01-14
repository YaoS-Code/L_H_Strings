package com.lhdstrings.servlet;

import com.lhdstrings.dao.UserDao;
import com.lhdstrings.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    public void init() {
        userDao = new UserDao(); // Ensure UserDao can validate user credentials
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDao.validateUser(email, password); // Implement this method in UserDao
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user.getFirstName()); // Store user or user identifier in session
                response.sendRedirect("index.jsp"); // Redirect to portal/dashboard page
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("loginError", "Invalid Email or password");
                response.sendRedirect("portalPage.jsp"); // Redirect back to the login page or home page
            }
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }
}
