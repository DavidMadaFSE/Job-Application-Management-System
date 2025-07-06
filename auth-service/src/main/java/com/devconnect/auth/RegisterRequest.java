package com.devconnect.auth;

public record RegisterRequest(String email, String password, String fullName) {}