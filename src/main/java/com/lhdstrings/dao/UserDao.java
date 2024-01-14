package com.lhdstrings.dao;

import com.lhdstrings.config.DatabaseConfig;
import com.lhdstrings.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource dataSource;

    public UserDao() {
        this.dataSource = DatabaseConfig.getDataSource();
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (first_name, last_name, email, birthday, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, Date.valueOf(user.getBirthday()));
            stmt.setString(5, user.getPassword());
            stmt.executeUpdate();
        }
    }

    public User validateUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?"; // Replace "username" with your actual column name
        User user = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            System.out.println("test");
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("test2");
                if (rs.next()) {
                    System.out.println("test3");
                    String storedPassword = rs.getString("password"); // Assuming password is stored in a column named 'password'
                    System.out.println(storedPassword);
                    if (BCrypt.checkpw(password, storedPassword)) { // Check if the provided password matches the stored one
                        user = new User();
                        // Assuming you have setters in User class for these fields
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setEmail(rs.getString("email"));
                        user.setBirthday(rs.getDate("birthday").toLocalDate());
                        // ... set other fields as needed
                    }
                }
            }
        }
        return user; // Returns null if user is not found or password does not match
    }

    // Other methods for update, delete, find...0
}
