package com.buccodev.app_register.infrastructure.services.usecases.exception;

public class EmailExistesException extends RuntimeException {
  public EmailExistesException(String message) {
    super(message);
  }
}
