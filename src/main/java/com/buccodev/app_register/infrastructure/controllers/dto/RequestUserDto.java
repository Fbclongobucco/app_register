package com.buccodev.app_register.infrastructure.controllers.dto;

import java.time.LocalDate;

public record RequestUserDto(String name, String email, String password, LocalDate birthday) {
}
