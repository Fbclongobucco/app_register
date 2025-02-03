package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class UpdateUserGateway {

    private final UpdateUserUsecase usecase;

    public UpdateUserGateway(UpdateUserUsecase usecase) {
        this.usecase = usecase;
    }

    public void updateUser(Long id, User user, String token){
        usecase.updateUser(id, user, token);
    }

    public void updatePassword(Long id, String password, String token){
     usecase.updatePasword(id, password, token);
    }

    public void updateIsActive(Long id, Boolean isActive, String token){
        usecase.updateIsActive(id, isActive, token);
    }
}
