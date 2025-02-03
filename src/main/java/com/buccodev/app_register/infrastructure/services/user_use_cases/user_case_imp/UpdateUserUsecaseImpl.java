package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.controllers.utils.TokerManager;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.mappers.UserMapper;
import com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions.TokenValidationException;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUsecaseImpl implements UpdateUserUsecase {

    private final UserDomainRepository repository;

    public UpdateUserUsecaseImpl(UserDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateUser(Long id, User user, String token) {

        UserDomain userRecovered = repository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));

        if (Boolean.FALSE.equals(TokerManager.verifyToken(userRecovered.getEmail(), token) && userRecovered.getId().equals(id))) {
            throw new TokenValidationException("invalid token!");
        }
        userRecovered.setName(user.getName());
        userRecovered.setEmail(user.getEmail());
        userRecovered.setBirthday(user.getBirthday());
        userRecovered.setActive(user.getActive());

        repository.save(userRecovered);
    }


    @Override
    public void updateIsActive(Long id, Boolean isActive, String token) {

        UserDomain userRecovered = repository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));

        if (Boolean.FALSE.equals(TokerManager.verifyToken(userRecovered.getEmail(), token) && userRecovered.getId().equals(id))) {
            throw new TokenValidationException("invalid token!");
        }

        userRecovered.setActive(isActive);

        repository.save(userRecovered);
    }

    @Override
    public void updatePasword(Long id, String password, String token) {

        UserDomain userRecovered = repository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));

        if (Boolean.FALSE.equals(TokerManager.verifyToken(userRecovered.getEmail(), token)
                && userRecovered.getId().equals(id))) {
            throw new TokenValidationException("invalid token!");
        }

        userRecovered.setPassword(password);

        repository.save(userRecovered);
    }



}
