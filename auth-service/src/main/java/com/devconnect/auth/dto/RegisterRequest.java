package com.devconnect.auth.dto;

public record RegisterRequest(String email, String password, String fullName) {}