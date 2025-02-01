package com.buccodev.app_register.infrastructure.controllers.excepition;

import java.time.LocalDateTime;

public record StandardError(String timeStamp, Integer status, String massager, String path) {
}
