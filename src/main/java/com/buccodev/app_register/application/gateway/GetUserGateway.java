package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.usecase.GetUserUsecase;
import com.buccodev.app_register.core.entities.User;

import java.util.List;

public class GetUserGateway {

    private final GetUserUsecase usecase;

    public GetUserGateway(GetUserUsecase usecase) {
        this.usecase = usecase;
    }

    public User getUserById(Long id){
        return usecase.getUserById(id);
    }

    public User getUserByEmail(String email){
        return usecase.getUserByEmail(email);
    }

    public List<User> getAllUsers(Integer page,  Integer size){
        return usecase.getAllUser(page, size);
    }

    public User login(String email, String password){
        return usecase.login(email, password);
    }

}
