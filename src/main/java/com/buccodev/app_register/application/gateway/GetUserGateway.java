package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.usecase.GetUserUsecase;
import com.buccodev.app_register.core.entities.User;

import java.util.List;
import java.util.Map;

public class GetUserGateway {

    private final GetUserUsecase usecase;

    public GetUserGateway(GetUserUsecase usecase) {
        this.usecase = usecase;
    }

    public User getUserById(Long id, String token){
        return usecase.getUserById(id, token);
    }

    public User getUserByEmail(String email, String token){
        return usecase.getUserByEmail(email, token);
    }

    public List<User> getAllUsers(Integer page,  Integer size, String token){
        return usecase.getAllUser(page, size, token);
    }

    public Map<User, String>  login(String email, String password){
        return usecase.login(email, password);
    }

}
