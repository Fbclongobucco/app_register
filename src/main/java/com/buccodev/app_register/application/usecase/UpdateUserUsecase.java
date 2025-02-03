package com.buccodev.app_register.application.usecase;

import com.buccodev.app_register.core.entities.User;

public interface UpdateUserUsecase {

    void updateUser(Long id, User user, String token);
    void updatePasword(Long id, String password, String token);
    void updateIsActive(Long id, Boolean isActive, String token);

}
