package com.buccodev.app_register.infrastructure.services.usecases.user_imp;

import com.buccodev.app_register.application.usecase.CreateUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.usecases.exception.EmailExistesException;
import com.buccodev.app_register.infrastructure.services.usecases.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {

    private final UserDomainRepository repository;

    public CreateUserUsecaseImpl(UserDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUser(User user) {
       if(isUserExistisByEmail(user.getEmail())) {
            throw new EmailExistesException("email already registered");
       }
       user.setActive(true);
        UserDomain usersave = repository.save(UserMapper.toUserDomainfromUser(user));
        return UserMapper.toUserFromUserDomain(usersave);
    }

    @Override
    public Boolean isUserExistisByEmail(String email) {

        return repository.existsByEmail(email) ;
    }
}
