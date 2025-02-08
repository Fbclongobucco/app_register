package com.buccodev.app_register.infrastructure.services.user_use_cases.user_case_imp;

import com.buccodev.app_register.application.usecase.GetUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.core.exception.PasswordValidationException;
import com.buccodev.app_register.infrastructure.controllers.utils.TokenManager;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.mappers.UserMapper;
import com.buccodev.app_register.infrastructure.services.user_use_cases.service_exceptions.TokenValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetUserUserUsecaseImpl implements GetUserUsecase {

    private final UserDomainRepository repository;
    private final TokenManager tokenManager;
    private final PasswordEncoder passwordEncoder;

    public GetUserUserUsecaseImpl(UserDomainRepository repository, TokenManager tokenManager, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.tokenManager = tokenManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(Long id, String token) {

        UserDomain userRecovery = repository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("user not found!"));


        if (tokenManager.verifyAdminToken(token) && !tokenManager.verifyToken(userRecovery.getEmail(), token)) {
                throw new TokenValidationException("Invalid token!");
            }

        return UserMapper.toUserFromUserDomain(userRecovery);

    }

    @Override
    public User getUserByEmail(String email, String token) {

        UserDomain userRecovery = repository.findByEmail(email).
                orElseThrow(()->new ResourceNotFoundException("email not found!"));

        if (tokenManager.verifyAdminToken(token) && !tokenManager.verifyToken(userRecovery.getEmail(), token)) {
            throw new TokenValidationException("Invalid token!");
        }

        return UserMapper.toUserFromUserDomain(userRecovery);
    }

    @Override
    public Map<User, String> login(String email, String password) {

        UserDomain userRecovery = repository.findByEmail(email).
                orElseThrow(()-> new ResourceNotFoundException("email not found!")) ;

        User user = UserMapper.toUserFromUserDomain(userRecovery);


        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordValidationException("the password is incorrect");
        }

        String userToken = tokenManager.generateToken(user.getEmail());

        Map<User, String> userTokenMap = new HashMap<>();

        userTokenMap.put(user, userToken);

        return userTokenMap;
    }

    public List<User> getAllUser(Integer page, Integer size, String token) {

        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        }

        if(tokenManager.verifyAdminToken(token)){
            throw new TokenValidationException("invalid token!");
        }

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UserDomain> usersPage = repository.findAll(pageRequest);


        return usersPage.map(UserMapper::toUserFromUserDomain).toList();
    }
}
