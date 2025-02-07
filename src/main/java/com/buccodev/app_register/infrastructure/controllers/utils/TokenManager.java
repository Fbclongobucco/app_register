package com.buccodev.app_register.infrastructure.controllers.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenManager {

    @Value("${admin.email}")
    private String emailAdmin;

    @Value("${admin.token}")
    private String tokenAdmin;

    private final Map<String, String> tokens = new HashMap<>();

    public String generateToken(String email) {
        String token = UUID.randomUUID().toString();
        tokens.put(email, token);
        return token;
    }

    public Boolean verifyToken(String email, String token) {

        return token.equals(tokens.get(email));
    }

    public void generateAdminToken() {
        tokens.put(emailAdmin, tokenAdmin);
    }

    public Boolean verifyAdminToken(String token) {

        return !token.equals(tokenAdmin);
    }
}
