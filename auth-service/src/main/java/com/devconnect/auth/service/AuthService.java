package com.devconnect.auth.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devconnect.auth.dto.AuthResponse;
import com.devconnect.auth.dto.LoginRequest;
import com.devconnect.auth.dto.RegisterRequest;
import com.devconnect.auth.model.User;
import com.devconnect.auth.repository.AuthRepository;
import com.devconnect.auth.security.JWTUtil;

@Service
public class AuthService {
    private AuthRepository authRepository;
    private JWTUtil jwtUtil;

    AuthService(AuthRepository authRepository, JWTUtil jwtUtil) {
        this.authRepository = authRepository;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(RegisterRequest request) {
        String userEmail = request.email();

        Optional <User> user = authRepository.findByEmail(userEmail);

        if (!user.isEmpty()) {
            System.out.println("User already exists");
            return;
        }

        String userPassword = hashPassword(request.password());

        User newUser = new User();
        newUser.setEmail(userEmail);
        newUser.setPassword(userPassword);

        authRepository.save(newUser);

        System.out.println("User registered successfully");
    }

    public AuthResponse loginUser(LoginRequest request) {
        String userEmail = request.email();
        String userPassword = request.password();

        User user = authRepository.findByEmail(userEmail)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials") );

        if (!checkPassword(userPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
        }

        String token = jwtUtil.generateToken(userEmail);

        System.out.println("Logging in...\nHere is your session token: " + token);

        return new AuthResponse(token);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private boolean checkPassword(String password, String hashPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hashPassword);
    }
}
