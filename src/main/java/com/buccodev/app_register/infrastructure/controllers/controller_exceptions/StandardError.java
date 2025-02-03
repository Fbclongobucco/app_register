package com.buccodev.app_register.infrastructure.controllers.controller_exceptions;

public record StandardError(String timeStamp, Integer status, String massager, String path) {
}
