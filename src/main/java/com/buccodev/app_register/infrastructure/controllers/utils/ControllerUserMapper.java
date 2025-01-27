package com.buccodev.app_register.infrastructure.controllers.utils;

import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.controllers.dto.RequestUserDto;
import com.buccodev.app_register.infrastructure.controllers.dto.ResponseUserDto;
import com.buccodev.app_register.infrastructure.controllers.dto.ResponseUserTokenDto;
import com.buccodev.app_register.infrastructure.controllers.dto.ResquestUpdateUser;

import java.util.List;

public class ControllerUserMapper {

    public static User toUserFromRequestUserDto(RequestUserDto dto){
       return new User(null, dto.name(), dto.birthday(), dto.email(), dto.password(), null);
    }

    public static ResponseUserDto toResponseUserDtoFromUser(User user){
        return new ResponseUserDto(user.getId(), user.getName(), user.getEmail(), user.getBirthday(), user.getActive());
    }

    public static List<ResponseUserDto> toResponseUserDtoListFromListUser(List<User> users){
        return users.stream().map(ControllerUserMapper::toResponseUserDtoFromUser).toList();
    }

    public static ResponseUserTokenDto responseUserTokenDtoFroUser(User user, String token){
        return new ResponseUserTokenDto(user.getId(), user.getName(), user.getEmail(), token);
    }

    public static User toRequestUserUpdadeFromUser(ResquestUpdateUser userDto){
        return new User(null, userDto.name(), userDto.birthday(), userDto.email(), "12345678", true);
    }

}
