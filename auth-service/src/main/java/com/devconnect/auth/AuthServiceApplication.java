package com.devconnect.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {
    public static void main(String[] args) {
        System.out.println("AuthService is now starting...");
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}