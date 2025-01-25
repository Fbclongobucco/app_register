package com.buccodev.app_register.application.gateway;


import com.buccodev.app_register.application.usecase.CreateUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class CreateUserGateway {

    private final CreateUserUsecase usecase;

    public CreateUserGateway(CreateUserUsecase usecase) {
        this.usecase = usecase;
    }

    public User saveUser(User user){
        return usecase.saveUser(user);
    }

}
