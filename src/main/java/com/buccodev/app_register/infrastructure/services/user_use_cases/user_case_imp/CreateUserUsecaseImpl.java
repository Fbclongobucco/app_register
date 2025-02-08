package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.CreateUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.EmailExistesException;
import com.buccodev.app_register.infrastructure.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUsecaseImpl implements CreateUserUsecase {

    private final UserDomainRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUsecaseImpl(UserDomainRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {

       if(isUserExistisByEmail(user.getEmail())) {
            throw new EmailExistesException("email already registered");
       }

       user.setPassword(passwordEncoder.encode(user.getPassword()));

       UserDomain userRecovery = UserMapper.toUserDomainfromUser(user);

       UserDomain userDomainSalved = repository.save(userRecovery);


        return UserMapper.toUserFromUserDomain(userDomainSalved);
    }

    @Override
    public Boolean isUserExistisByEmail(String email) {

        return repository.existsByEmail(email) ;
    }
}
