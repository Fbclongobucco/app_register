package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.CreateUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions.EmailExistesException;
import com.buccodev.app_register.infrastructure.mappers.UserMapper;
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

       UserDomain userDomain = UserMapper.toUserDomainfromUser(user);

       UserDomain userDomainSalved = repository.save(userDomain);


        return UserMapper.toUserFromUserDomain(userDomainSalved);
    }

    @Override
    public Boolean isUserExistisByEmail(String email) {

        return repository.existsByEmail(email) ;
    }
}
