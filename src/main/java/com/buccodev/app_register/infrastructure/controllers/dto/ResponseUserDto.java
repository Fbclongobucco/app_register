package com.buccodev.app_register.infrastructure.controllers.dto;

import java.time.LocalDate;

public record ResponseUserDto(Long id, String name, String email, LocalDate birthday, Boolean isActive) {
}
