package com.buccodev.app_register.infrastructure.controllers.controller_exceptions;

import com.buccodev.app_register.core.exception.EmailValidationException;
import com.buccodev.app_register.core.exception.PasswordValidationException;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.EmailExistesException;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.TokenValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EmailValidationException.class)
    public ResponseEntity<StandardError> emailValidationErrorHandler(EmailValidationException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        StandardError err = new StandardError(formattedTimestamp, status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PasswordValidationException.class)
    public ResponseEntity<StandardError> passwordValidationErrorHandler(PasswordValidationException e,
                                                                        HttpServletRequest request){

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        StandardError err = new StandardError(formattedTimestamp, status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailExistesException.class)
    public ResponseEntity<StandardError> emailExistsExceptionHandler(EmailExistesException e,
                                                                     HttpServletRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        StandardError err = new StandardError(formattedTimestamp, status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundHandler(ResourceNotFoundException e,
                                                                 HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        StandardError err = new StandardError(formattedTimestamp, status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<StandardError> tokenValidationExceptionHandler(TokenValidationException e,
                                                                 HttpServletRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        StandardError err = new StandardError(formattedTimestamp, status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> ilegalArgumentExceptionHandler(IllegalArgumentException e,
                                                                         HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        StandardError err = new StandardError(formattedTimestamp, status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
