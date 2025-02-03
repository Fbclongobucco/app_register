package com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions;

public class EmailExistesException extends RuntimeException {
    public EmailExistesException(String message) {
        super(message);
    }
}
