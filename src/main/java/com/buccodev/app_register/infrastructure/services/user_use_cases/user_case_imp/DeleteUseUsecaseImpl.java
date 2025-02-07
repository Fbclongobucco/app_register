package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.DeleteUserUsecase;
import com.buccodev.app_register.infrastructure.controllers.utils.TokenManager;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.TokenValidationException;
import org.springframework.stereotype.Service;

@Service
public class DeleteUseUsecaseImpl implements DeleteUserUsecase {

    private final UserDomainRepository repository;
    private final TokenManager tokenManager;

    public DeleteUseUsecaseImpl(UserDomainRepository repository, TokenManager tokenManager) {
        this.repository = repository;
        this.tokenManager = tokenManager;
    }

    @Override
    public void deleteUserById(Long id, String token) {
        UserDomain userRecovery = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user to be deleted not found!"));

        if (tokenManager.verifyAdminToken(token) && !tokenManager.verifyToken(userRecovery.getEmail(), token)) {
            throw new TokenValidationException("Invalid token!");
        }
        repository.deleteById(userRecovery.getId());
    }

    @Override
    public void deleteAllUsers(String token) {

        if(tokenManager.verifyAdminToken(token)){
            throw new TokenValidationException("invalid token!");
        }

        repository.deleteAll();
    }


}
