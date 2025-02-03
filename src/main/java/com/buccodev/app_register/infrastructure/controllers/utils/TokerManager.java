package com.buccodev.app_register.infrastructure.controllers.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokerManager {

    private static final String EMAIL_ADMIN = "longobuccofbc@gmail.com";
    private static final String TOKEN_ADMIN = "longobucco_secret_admin";
    private static final Map<String, String> tokens = new HashMap<>();

    public static String generateToken(String email){
        String token = UUID.randomUUID().toString();
        tokens.put(email, token);
        return token;
    }

    public static Boolean verifyToken(String email, String token){
        return token.equals(tokens.get(email));
    }

    public static void generateAdminToken(){
        tokens.put(EMAIL_ADMIN, TOKEN_ADMIN);
    }

    public static Boolean verifyAdminToken(String token){
        return (TokerManager.verifyToken(EMAIL_ADMIN, token));
    }
}
