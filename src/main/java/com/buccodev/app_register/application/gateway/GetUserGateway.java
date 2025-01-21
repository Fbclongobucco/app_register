package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.exception.UserNotFoundException;
import com.buccodev.app_register.application.usecase.GetUserUsecase;
import com.buccodev.app_register.core.entities.User;

import java.util.List;

public class GetUserGateway {

    private final GetUserUsecase usecase;

    public GetUserGateway(GetUserUsecase usecase) {
        this.usecase = usecase;
    }

    public User getUserById(Long id){
        User recoveredUser = usecase.getUserById(id);
        if(recoveredUser == null){
            throw new UserNotFoundException("User not found!");
        }
        return recoveredUser;
    }

    public User getUserByEmail(String email){
        User recoveredUser = usecase.getUserByEmail(email);
        if(recoveredUser == null){
            throw new UserNotFoundException("User not found!");
        }
        return recoveredUser;
    }

    public List<User> getAllUsers(){
        return usecase.getAllUser();
    }


}
