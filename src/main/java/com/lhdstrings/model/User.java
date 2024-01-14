package com.lhdstrings.model;

import java.time.LocalDate;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String password; // Store hashed password

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(int id, String firstName, String lastName, String email, LocalDate birthday, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.password = password; // Note: Password should be hashed before setting
    }

    // Getters and Setters with Basic Validation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(User user, String loginPassword) {
        return BCrypt.checkpw(loginPassword, user.getPassword());
    }
}
