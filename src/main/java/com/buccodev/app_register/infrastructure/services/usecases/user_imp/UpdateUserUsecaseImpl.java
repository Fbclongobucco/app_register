package com.buccodev.app_register.infrastructure.services.usecases.user_imp;

import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.usecases.exception.ResourceNotFoundException;
import com.buccodev.app_register.infrastructure.services.usecases.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUsecaseImpl implements UpdateUserUsecase {

    private final UserDomainRepository repository;

    public UpdateUserUsecaseImpl(UserDomainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateUser(Long id, User user) {
        UserDomain userRecovered = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));
        userRecovered.setName(user.getName());
        userRecovered.setEmail(user.getEmail());
        userRecovered.setBirthday(user.getBirthday());
        userRecovered.setActive(user.getActive());

        repository.save(userRecovered);
    }

    @Override
    public void updatePasword(Long id, String password) {
        UserDomain userDomain = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));
        User userRecovered = UserMapper.toUserFromUserDomain(userDomain);
        userRecovered.setPassword(password);
        UserDomain userUpdated = UserMapper.toUserDomainfromUser(userRecovered);
        repository.save(userUpdated);
    }

    @Override
    public void updateIsActive(Long id, Boolean isActive) {
        UserDomain userDomain = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user to be updated not found!"));
        User userRecovered = UserMapper.toUserFromUserDomain(userDomain);
        userRecovered.setActive(isActive);
        UserDomain userUpdated = UserMapper.toUserDomainfromUser(userRecovered);
        repository.save(userUpdated);
    }

}
