package com.buccodev.app_register.application.gateway;

import com.buccodev.app_register.application.exception.UserNotFoundException;
import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import com.buccodev.app_register.core.entities.User;

public class UpdateUserGateway {

    private final UpdateUserUsecase usecase;

    public UpdateUserGateway(UpdateUserUsecase usecase) {
        this.usecase = usecase;
    }

    public void updateUser(Long id, User user){
        User recoveredUser = usecase.findUserById(id);
        if(recoveredUser == null){
            throw new UserNotFoundException("User to be updated not found!");
        }
        recoveredUser.setName(user.getName());
        recoveredUser.setEmail(user.getEmail());
        recoveredUser.setBirthday(user.getBirthday());
        recoveredUser.setActive(user.getActive());

        usecase.updateUser(user.getId(), recoveredUser);
    }

    public void updatePassword(Long id, String password){
        User recovered = usecase.findUserById(id);

        if(recovered == null){
            throw new UserNotFoundException("user to have updated password was not found!");
        }
        recovered.setPassword(password);
        usecase.updateUser(id, recovered);
    }
}
