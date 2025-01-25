package com.buccodev.app_register.infrastructure.services.usecases.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
