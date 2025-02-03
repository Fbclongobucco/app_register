package com.buccodev.app_register.infrastructure.services.user_use_cases.dto;

import java.time.LocalDate;

public record ResquestUpdateUser(String name, String email, LocalDate birthday) {
}
