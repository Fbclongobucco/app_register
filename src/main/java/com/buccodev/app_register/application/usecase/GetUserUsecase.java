package com.buccodev.app_register.application.usecase;

import com.buccodev.app_register.core.entities.User;

import java.util.List;
import java.util.Map;

public interface GetUserUsecase {

    User getUserById(Long id, String token);
    User getUserByEmail(String email, String token);
    Map<User, String> login(String email, String password);
    List<User> getAllUser(Integer page, Integer size, String token);

}
