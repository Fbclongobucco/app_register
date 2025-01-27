package com.buccodev.app_register.infrastructure.services.usecases.user_imp;

import com.buccodev.app_register.application.usecase.GetUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.core.exception.PasswordValidationException;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.usecases.exception.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.services.usecases.mappers.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUserUsecaseImpl implements GetUserUsecase {

    private final UserDomainRepository repository;

    public GetUserUserUsecaseImpl(UserDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(Long id) {
        UserDomain userDomain = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found!"));
        return UserMapper.toUserFromUserDomain(userDomain);
    }

    @Override
    public User getUserByEmail(String email) {

        UserDomain userDomain = repository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("email not found!"));

        return UserMapper.toUserFromUserDomain(userDomain);
    }

    @Override
    public User login(String email, String password) {

        User user = getUserByEmail(email);

        if(!user.ifThePasswordMatches(password)){
            throw new PasswordValidationException("the password is incorrect");
        }

        return user;
    }

    public List<User> getAllUser(Integer page, Integer size) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        }

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UserDomain> usersPage = repository.findAll(pageRequest);


        return usersPage.map(UserMapper::toUserFromUserDomain).toList();
    }
}
