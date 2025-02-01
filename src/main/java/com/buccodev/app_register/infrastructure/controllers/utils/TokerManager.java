package com.buccodev.app_register.infrastructure.controllers.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokerManager {

    private static final Map<String, String> tokens = new HashMap<>();

    public static String generateToken(String email){
        String token = UUID.randomUUID().toString();
        tokens.put(email, token);

        return token;
    }

    public static Boolean verifyToken(String email, String token){
        return token.equals(tokens.get(email));
    }


}
