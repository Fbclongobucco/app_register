package com.buccodev.app_register.infrastructure.services.usecases.mappers;

import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;

public class UserMapper {

    public static UserDomain toUserDomainfromUser(User user){
        return new UserDomain(user.getId(), user.getName(), user.getBirthday(),
                user.getEmail(), user.getPassword(), user.getActive());
    }

    public static User toUserFromUserDomain(UserDomain userDomain){
        return new User(userDomain.getId(), userDomain.getName(), userDomain.getBirthday(),
                userDomain.getEmail(), userDomain.getPassword(), userDomain.getActive());
    }
}
