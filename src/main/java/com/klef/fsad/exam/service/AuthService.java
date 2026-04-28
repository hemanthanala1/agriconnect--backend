package com.klef.fsad.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.fsad.exam.dto.LoginRequest;
import com.klef.fsad.exam.dto.LoginResponse;
import com.klef.fsad.exam.dto.RegisterRequest;
import com.klef.fsad.exam.model.Role;
import com.klef.fsad.exam.model.User;
import com.klef.fsad.exam.repository.UserRepository;
import com.klef.fsad.exam.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private PasswordEncoder encoder;

    public LoginResponse register(RegisterRequest req) {
        User user = new User();
        user.setFirstName(req.firstName);
        user.setLastName(req.lastName);
        user.setEmail(req.email);
        user.setPassword(encoder.encode(req.password));
        user.setRole(Role.valueOf(req.role));

        User savedUser = repo.save(user);

        // Auto-login after registration
        String token = jwt.generateToken(
                savedUser.getEmail(),
                savedUser.getRole().name()
        );

        return new LoginResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getRole().name(),
                token
        );
    }

    public LoginResponse login(LoginRequest req) {

        User user = repo.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.password, user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // 🔥 PASS ROLE HERE
        String token = jwt.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        // Return complete user data with token
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name(),
                token
        );
    }
}