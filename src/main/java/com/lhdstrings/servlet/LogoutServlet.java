package com.lhdstrings.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logout") // Update this to match the URL pattern you use for logout
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the current session
        HttpSession session = request.getSession(false); // Get the current session and don't create a new one if it doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }

        // Redirect to the desired page after logout
        response.sendRedirect("index.jsp"); // You can redirect to the home page or login page as per your requirement
    }
}
