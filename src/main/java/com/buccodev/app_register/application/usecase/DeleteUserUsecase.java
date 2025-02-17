package com.buccodev.app_register.application.usecase;

import com.buccodev.app_register.core.entities.User;

public interface DeleteUserUsecase {

    void deleteUserById(Long id, String token);
    void deleteAllUsers(String token);
}
