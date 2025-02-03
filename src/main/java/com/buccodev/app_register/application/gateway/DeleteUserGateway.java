package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.usecase.DeleteUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class DeleteUserGateway {

    private final DeleteUserUsecase usecase;

    public DeleteUserGateway(DeleteUserUsecase usecase) {
        this.usecase = usecase;
    }

    public void deleteUserById(Long id, String token){
        usecase.deleteUserById(id, token);
    }

    public void deleteAllUser(String token){
        usecase.deleteAllUsers(token);
    }
}
