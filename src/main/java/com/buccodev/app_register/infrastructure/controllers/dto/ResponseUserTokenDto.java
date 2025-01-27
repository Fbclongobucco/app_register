package com.buccodev.app_register.infrastructure.controllers.dto;

public record ResponseUserTokenDto(Long id, String name, String email, String token) {
}
