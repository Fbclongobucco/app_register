package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.exception.IfExistisByEmail;
import com.buccodev.app_register.application.usecase.CreateUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class CreateUserGateway {

    private final CreateUserUsecase usecase;

    public CreateUserGateway(CreateUserUsecase usecase) {
        this.usecase = usecase;
    }

    public User saveUser(User user){
        user.emailValidation(user.getEmail());
        user.passwordValidate(user.getPassword());
        if(usecase.isUserExistisByEmail(user.getEmail())){
            throw new IfExistisByEmail("the user already exists!");
        }
        return usecase.saveUser(user);
    }

}
