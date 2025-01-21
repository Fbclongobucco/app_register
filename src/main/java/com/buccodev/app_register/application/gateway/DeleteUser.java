package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.exception.UserNotFoundException;
import com.buccodev.app_register.application.usecase.DeleteUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class DeleteUser {

    private final DeleteUserUsecase usecase;

    public DeleteUser(DeleteUserUsecase usecase) {
        this.usecase = usecase;
    }

    public void deleteUserById(Long id){
        User user = usecase.findUserById(id);
        if(user == null){
            throw new UserNotFoundException("Unable to find user to delete!");
        }
        usecase.deleteUserById(user.getId());
    }

    public void deleteAllUser(){
        usecase.deleteAllUsers();
    }
}
