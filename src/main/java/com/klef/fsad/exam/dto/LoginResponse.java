package com.klef.fsad.exam.dto;

public class LoginResponse {
    public Long id;
    public String email;
    public String firstName;
    public String lastName;
    public String userType;
    public String token;

    // Constructor with all fields
    public LoginResponse(Long id, String email, String firstName, String lastName, String userType, String token) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.token = token;
    }

    // Default constructor
    public LoginResponse() {
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}