package com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(String message) {
        super(message);
    }
}
