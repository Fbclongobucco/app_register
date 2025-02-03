package com.buccodev.app_register.infrastructure.services.user_use_cases.dto;

public record ResponseUserTokenDto(Long id, String name, String email, String token) {
}
