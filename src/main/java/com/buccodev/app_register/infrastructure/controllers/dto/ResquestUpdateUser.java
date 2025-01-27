package com.buccodev.app_register.infrastructure.controllers.dto;

import java.time.LocalDate;

public record ResquestUpdateUser(String name, String email, LocalDate birthday) {
}
