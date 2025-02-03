package com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
