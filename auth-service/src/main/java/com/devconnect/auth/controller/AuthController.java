package com.devconnect.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devconnect.auth.dto.AuthResponse;
import com.devconnect.auth.dto.LoginRequest;
import com.devconnect.auth.dto.RegisterRequest;
import com.devconnect.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test")
    public String test() {
        return "It works!";
    }
    
    @PostMapping("/register")
    public void registerUser(@RequestBody RegisterRequest request ) {
        authService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }
}
