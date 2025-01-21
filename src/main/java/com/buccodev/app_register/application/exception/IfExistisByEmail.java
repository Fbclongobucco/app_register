package com.buccodev.app_register.application.exception;

public class IfExistisByEmail extends RuntimeException {
    public IfExistisByEmail(String message) {
        super(message);
    }
}
