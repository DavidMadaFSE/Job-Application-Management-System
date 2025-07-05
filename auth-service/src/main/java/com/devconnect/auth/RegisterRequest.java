package com.devconnect.auth;

import org.springframework.stereotype.Component;

@Component
public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return fullName;
    }
}