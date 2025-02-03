package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.DeleteUserUsecase;
import com.buccodev.app_register.infrastructure.controllers.utils.TokerManager;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.TokenValidationException;
import org.springframework.stereotype.Service;

@Service
public class DeleteUseUsecaseImpl implements DeleteUserUsecase {

    private final UserDomainRepository repository;

    public DeleteUseUsecaseImpl(UserDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteUserById(Long id, String token) {
        UserDomain userDomain = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user to be deleted not found!"));

        boolean isUserTokenValid = TokerManager.verifyToken(userDomain.getEmail(), token);
        boolean isAdminTokenValid = TokerManager.verifyAdminToken(token);

        if (!isUserTokenValid && !isAdminTokenValid) {
            throw new TokenValidationException("Invalid token!");
        }
        repository.deleteById(userDomain.getId());
    }

    @Override
    public void deleteAllUsers(String token) {

        if(Boolean.FALSE.equals(TokerManager.verifyAdminToken(token))){
            throw new TokenValidationException("invalid token!");
        }

        repository.deleteAll();
    }


}
