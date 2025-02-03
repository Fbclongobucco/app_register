package com.buccodev.app_register.infrastructure.services.user_use_cases.dto;

import java.time.LocalDate;

public record RequestUserDto(String name, String email, String password, LocalDate birthday) {
}
