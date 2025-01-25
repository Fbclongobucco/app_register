package com.buccodev.app_register.infrastructure.controllers;

import com.buccodev.app_register.application.gateway.CreateUserGateway;
import com.buccodev.app_register.application.gateway.DeleteUserGateway;
import com.buccodev.app_register.application.gateway.GetUserGateway;
import com.buccodev.app_register.application.gateway.UpdateUserGateway;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.controllers.dto.RequestUserDto;
import com.buccodev.app_register.infrastructure.controllers.dto.ResponseUserDto;
import com.buccodev.app_register.infrastructure.controllers.utils.ControllerUserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final CreateUserGateway createUserGateway;
    private final DeleteUserGateway deleteUserGateway;
    private final GetUserGateway getUserGateway;
    private final UpdateUserGateway updateUserGateway;


    public UserController(CreateUserGateway createUserGateway, DeleteUserGateway deleteUserGateway, GetUserGateway getUserGateway, UpdateUserGateway updateUserGateway) {
        this.createUserGateway = createUserGateway;
        this.deleteUserGateway = deleteUserGateway;
        this.getUserGateway = getUserGateway;
        this.updateUserGateway = updateUserGateway;
    }

    @PostMapping
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody RequestUserDto requestUserDto) {

        User user = ControllerUserMapper.toUserFromRequestUserDto(requestUserDto);
        User userSalvered = createUserGateway.saveUser(user);
        ResponseUserDto responseUserDto = ControllerUserMapper.toResponseUserDtoFromUser(userSalvered);

        return ResponseEntity.created(URI.create("/api/v1/"+responseUserDto.id())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long userId){

        User user = getUserGateway.getUserById(userId);
        ResponseUserDto responseUserDto = ControllerUserMapper.toResponseUserDtoFromUser(user);

        return ResponseEntity.ok(responseUserDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> getAllUsers(){

        List<User> userList = getUserGateway.getAllUsers();
        List<ResponseUserDto> userDtoList = ControllerUserMapper.toResponseUserDtoListFromListUser(userList);

        return ResponseEntity.ok(userDtoList);
    }

}
