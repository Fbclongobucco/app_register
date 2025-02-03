package com.buccodev.app_register.infrastructure.controllers;

import com.buccodev.app_register.application.gateway.CreateUserGateway;
import com.buccodev.app_register.application.gateway.DeleteUserGateway;
import com.buccodev.app_register.application.gateway.GetUserGateway;
import com.buccodev.app_register.application.gateway.UpdateUserGateway;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.mappers.UserMapper;
import com.buccodev.app_register.infrastructure.services.user_use_cases.dto.*;
import com.buccodev.app_register.infrastructure.services.user_use_cases.server_exceptions.TokenValidationException;
import com.buccodev.app_register.infrastructure.controllers.utils.TokerManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
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

        User requestUser = UserMapper.toUserFromRequestUserDto(requestUserDto);

        User userCreated = createUserGateway.saveUser(requestUser);

        ResponseUserDto responseUserDto = UserMapper.toResponseUserDtoFromUser(userCreated);

        return ResponseEntity.created(URI.create("/api/v1/"+responseUserDto.id())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long userId, @RequestParam String token ){

        User user = getUserGateway.getUserById(userId, token);

        ResponseUserDto userDto = UserMapper.toResponseUserDtoFromUser(user);

        return ResponseEntity.ok(userDto);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseUserDto> getUserByEmail(@PathVariable String  email, @RequestParam String token ){

        User user = getUserGateway.getUserByEmail(email, token);

        ResponseUserDto userDto = UserMapper.toResponseUserDtoFromUser(user);

        return ResponseEntity.ok(userDto);

    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "20") int size,
                                                             @RequestParam String token){

        List<User> userList = getUserGateway.getAllUsers(page, size, token);

        List<ResponseUserDto> userDtoList = UserMapper.toResponseUserDtoListFromListUser(userList);

        return ResponseEntity.ok(userDtoList);
    }
    
    @GetMapping("/login")
    public ResponseEntity<ResponseUserTokenDto> login(@RequestBody LoginPayloadDto loginPayloadDto){

        Map <User, String> userAndToken = getUserGateway.login(loginPayloadDto.email(), loginPayloadDto.password());

        User user = userAndToken.keySet().iterator().next();

        String token = userAndToken.get(user);

        ResponseUserTokenDto responseUserTokenDto = new ResponseUserTokenDto(user.getId(),
                user.getName(), user.getEmail(), token);

        return ResponseEntity.ok(responseUserTokenDto);

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(@RequestParam String token){
        deleteUserGateway.deleteAllUser(token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserBy(@PathVariable Long userId, @RequestParam String token){
        deleteUserGateway.deleteUserById(userId, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId,
                                           @RequestParam String token,
                                           @RequestBody ResquestUpdateUser resquestUpdateUser){

        User user = UserMapper.toUserFromUserRequestUpdateDto(resquestUpdateUser);

        updateUserGateway.updateUser(userId, user, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatepassword/{userId}")
    public ResponseEntity<Void> updatePassord(@PathVariable Long userId,
                                              @RequestParam String token,
                                              @RequestBody UpdatePasswordDto password){

        updateUserGateway.updatePassword(userId, password.password(), token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateisactive/{userId}")
    public ResponseEntity<Void> updateIsActive(@PathVariable Long userId,
                                               @RequestParam String token,
                                               @RequestBody UpdateIsActiveDto isActive){


        updateUserGateway.updateIsActive(userId, isActive.isActive(), token);
        return ResponseEntity.noContent().build();
    }

}
