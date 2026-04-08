package com.klef.fsad.exam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "First name required")
    public String firstName;

    @NotBlank(message = "Last name required")
    public String lastName;

    @Email(message = "Invalid email")
    public String email;

    @Size(min = 6, message = "Password must be 6+ chars")
    public String password;

    @NotBlank(message = "Role required")
    public String role;
}