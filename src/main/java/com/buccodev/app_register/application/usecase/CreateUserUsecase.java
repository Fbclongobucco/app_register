package com.buccodev.app_register.application.usecase;

import com.buccodev.app_register.core.entities.User;

public interface CreateUserUsecase {

    User saveUser(User user);
    Boolean isUserExistisByEmail(String email);
}
