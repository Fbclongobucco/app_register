package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.controllers.utils.TokenManager;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.TokenValidationException;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUsecaseImpl implements UpdateUserUsecase {

    private final UserDomainRepository repository;
    private final TokenManager tokenManager;

    public UpdateUserUsecaseImpl(UserDomainRepository repository, TokenManager tokenManager) {
        this.repository = repository;
        this.tokenManager = tokenManager;
    }

    @Override
    public void updateUser(Long id, User user, String token) {

        UserDomain userRecovered = repository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));

        if (tokenManager.verifyAdminToken(token) && !tokenManager.verifyToken(userRecovered.getEmail(), token)) {
            throw new TokenValidationException("Invalid token!");
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

        if (tokenManager.verifyAdminToken(token) && !tokenManager.verifyToken(userRecovered.getEmail(), token)) {
            throw new TokenValidationException("Invalid token!");
        }
        userRecovered.setActive(isActive);

        repository.save(userRecovered);
    }

    @Override
    public void updatePasword(Long id, String password, String token) {

        UserDomain userRecovered = repository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));

        if (tokenManager.verifyAdminToken(token) && !tokenManager.verifyToken(userRecovered.getEmail(), token)) {
            throw new TokenValidationException("Invalid token!");
        }
        userRecovered.setPassword(password);

        repository.save(userRecovered);
    }
}
