package com.buccodev.app_register.infrastructure.mappers;

import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import com.buccodev.app_register.infrastructure.services.user_use_cases.dto.RequestUserDto;
import com.buccodev.app_register.infrastructure.services.user_use_cases.dto.ResponseUserDto;
import com.buccodev.app_register.infrastructure.services.user_use_cases.dto.ResponseUserTokenDto;
import com.buccodev.app_register.infrastructure.services.user_use_cases.dto.ResquestUpdateUser;

import java.util.List;

public class UserMapper {

    public static UserDomain toUserDomainfromUser(User user){
        return new UserDomain(user.getId(), user.getName(), user.getBirthday(),
                user.getEmail(), user.getPassword(), user.getActive());
    }

    public static User toUserFromUserDomain(UserDomain userDomain){
        return new User(userDomain.getId(), userDomain.getName(), userDomain.getBirthday(),
                userDomain.getEmail(), userDomain.getPassword(), userDomain.getActive());
    }

    public static User toUserFromRequestUserDto(RequestUserDto dto){
        return new User(null, dto.name(), dto.birthday(), dto.email(), dto.password(), true);
    }

    public static ResponseUserDto toResponseUserDtoFromUser(User user){
        return new ResponseUserDto(user.getId(), user.getName(), user.getEmail(), user.getBirthday(), user.getActive());
    }

    public static List<ResponseUserDto> toResponseUserDtoListFromListUser(List<User> users){
        return users.stream().map(UserMapper::toResponseUserDtoFromUser).toList();
    }

    public static ResponseUserTokenDto responseUserTokenDtoFroUser(User user, String token){
        return new ResponseUserTokenDto(user.getId(), user.getName(), user.getEmail(), token);
    }

    public static User toRequestUserUpdadeFromUser(ResquestUpdateUser userDto){
        return new User(null, userDto.name(), userDto.birthday(), userDto.email(), "12345678", true);
    }

    public static ResponseUserDto toResponseUserDtoFromUserDomain(UserDomain userDomain){
        return new ResponseUserDto(userDomain.getId(), userDomain.getName(), userDomain.getEmail(),
                userDomain.getBirthday(), userDomain.getActive());
    }

    public static User toUserFromUserRequestUpdateDto(ResquestUpdateUser resquestUpdateUser){
        return new User(null, resquestUpdateUser.name(),
                resquestUpdateUser.birthday(), resquestUpdateUser.email(), "12345678", true );
    }

}
