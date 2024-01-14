package com.lhdstrings.servlet;

import com.lhdstrings.dao.UserDao;
import com.lhdstrings.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String birthdayStr = request.getParameter("birthday");
        String password = request.getParameter("password");

        try {
            // Validate inputs
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("First name is required.");
            }
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last name is required.");
            }
            if (email == null || !email.contains("@")) { // Basic validation, consider using regex for stricter validation
                throw new IllegalArgumentException("Invalid email.");
            }
            LocalDate birthday;
            try {
                birthday = LocalDate.parse(birthdayStr);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid birthday format.");
            }
            if (password == null || password.length() < 6) {
                throw new IllegalArgumentException("Password must be at least 6 characters long.");
            }

            // Create and add user
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setBirthday(birthday);
            newUser.setPassword(password);

            userDao.addUser(newUser);

            // Redirect to login page or show registration success
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser.getFirstName());
            response.sendRedirect("index.jsp"); // Update with your login page
        } catch (IllegalArgumentException | SQLException e) {

            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", e.getMessage());
            session.setAttribute("showModal", "true");  // Set a flag to indicate the modal should be shown

            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; // Stop further execution
        }
    }
}
