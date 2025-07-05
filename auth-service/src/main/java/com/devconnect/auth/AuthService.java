package com.devconnect.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthRepository authRepository;

    AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void registerUser(RegisterRequest request) {
        String userEmail = request.getEmail();

        Optional <User> user = authRepository.findByEmail(userEmail);

        if (!user.isEmpty()) {
            System.out.println("User already exists");
            return;
        }

        String userPassword = hashPassword(request.getPassword());

        User newUser = new User();
        newUser.setEmail(userEmail);
        newUser.setPassword(userPassword);
        newUser.setName(request.getName());

        authRepository.save(newUser);

        System.out.println("User registered successfully");
    }

    public String loginUser(LoginRequest request) {
        // TODO: Check the users email and password. If they match return a token and log them into the system
        String userEmail = request.getEmail();
        String userPassword = request.getPassword();

        User user = authRepository.findByEmail(userEmail)
            .orElseThrow();

        if (!checkPassword(userPassword, user.getPassword())) {
            
        }

        return "";
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private boolean checkPassword(String password, String hashPassword) {
        return false;
    }
}
