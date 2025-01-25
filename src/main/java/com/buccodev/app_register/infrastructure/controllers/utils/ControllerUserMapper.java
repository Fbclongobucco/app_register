package com.buccodev.app_register.infrastructure.controllers;

import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.controllers.dto.RequestUserDto;
import com.buccodev.app_register.infrastructure.controllers.dto.ResponseUserDto;

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

}
