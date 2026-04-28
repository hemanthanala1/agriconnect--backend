package com.klef.fsad.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.exam.dto.LoginRequest;
import com.klef.fsad.exam.dto.LoginResponse;
import com.klef.fsad.exam.dto.RegisterRequest;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.UserRepository;
import com.klef.fsad.exam.service.AuthService;
import com.klef.fsad.exam.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public ApiResponse<LoginResponse> register(@RequestBody RegisterRequest request) {
        try {
            LoginResponse response = authService.register(request);
            return new ApiResponse<>("success", "Registration successful", response);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return new ApiResponse<>("success", "Login successful", response);
        } catch (Exception e) {
            return new ApiResponse<>("error", e.getMessage(), null);
        }
    }
}