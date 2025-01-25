package com.buccodev.app_register.infrastructure.services.usecases.user_imp;

import com.buccodev.app_register.application.usecase.DeleteUserUsecase;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.usecases.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteUseUsecaseImpl implements DeleteUserUsecase {

    private final UserDomainRepository repository;

    public DeleteUseUsecaseImpl(UserDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteUserById(Long id) {
        UserDomain userDomain = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user to be deleted not found!"));
        repository.deleteById(userDomain.getId());
    }

    @Override
    public void deleteAllUsers() {
        repository.deleteAll();
    }


}
