package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class UpdateUserGateway {

    private final UpdateUserUsecase usecase;

    public UpdateUserGateway(UpdateUserUsecase usecase) {
        this.usecase = usecase;
    }

    public void updateUser(Long id, User user){
        usecase.updateUser(id, user);
    }

    public void updatePassword(Long id, String password){
     usecase.updatePasword(id, password);
    }
}
