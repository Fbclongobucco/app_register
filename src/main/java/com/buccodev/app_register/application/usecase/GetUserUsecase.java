package com.buccodev.app_register.application.usecase;

import com.buccodev.app_register.core.entities.User;

import java.util.List;

public interface GetUserUsecase {

    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUser();

}