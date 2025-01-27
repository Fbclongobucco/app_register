package com.buccodev.app_register.infrastructure.controllers;

import com.buccodev.app_register.application.gateway.CreateUserGateway;
import com.buccodev.app_register.application.gateway.DeleteUserGateway;
import com.buccodev.app_register.application.gateway.GetUserGateway;
import com.buccodev.app_register.application.gateway.UpdateUserGateway;
import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.controllers.dto.*;
import com.buccodev.app_register.infrastructure.controllers.excepition.TokenValidationException;
import com.buccodev.app_register.infrastructure.controllers.utils.ControllerUserMapper;
import com.buccodev.app_register.infrastructure.controllers.utils.TokerManager;
import com.buccodev.app_register.infrastructure.services.usecases.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:5500")
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
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long userId, @RequestParam String token ){

        User user = getUserGateway.getUserById(userId);

        if (!TokerManager.verifyToken(user.getEmail(), token)) {
            throw new TokenValidationException("invalid token!");
        }
        ResponseUserDto userDto = ControllerUserMapper.toResponseUserDtoFromUser(user);
        return ResponseEntity.ok(userDto);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseUserDto> getUserByEmail(@PathVariable String  email, @RequestParam String token ){

        User user = getUserGateway.getUserByEmail(email);

        if (!TokerManager.verifyToken(user.getEmail(), token)) {
            throw new TokenValidationException("invalid token!");
        }
        ResponseUserDto userDto = ControllerUserMapper.toResponseUserDtoFromUser(user);
        return ResponseEntity.ok(userDto);

    }

    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){

        List<User> userList = getUserGateway.getAllUsers(page, size);
        List<ResponseUserDto> userDtoList = ControllerUserMapper.toResponseUserDtoListFromListUser(userList);

        return ResponseEntity.ok(userDtoList);
    }
    @GetMapping("/login")
    public ResponseEntity<ResponseUserTokenDto> login(@RequestBody LoginPayloadDto loginPayloadDto){
        User user = getUserGateway.login(loginPayloadDto.email(), loginPayloadDto.password());

        ResponseUserDto userDto = ControllerUserMapper.toResponseUserDtoFromUser(user);

        String token = TokerManager.generateToken(userDto.email());

        ResponseUserTokenDto responseUserTokenDto = new ResponseUserTokenDto(userDto.id(),
                userDto.name(), userDto.email(), token);

        return ResponseEntity.ok(responseUserTokenDto);

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        deleteUserGateway.deleteAllUser();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserBy(@PathVariable Long userId ){
        deleteUserGateway.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody ResquestUpdateUser userDto){
        User user = ControllerUserMapper.toRequestUserUpdadeFromUser(userDto);
        updateUserGateway.updateUser(userId, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatepassword/{userId}")
    public ResponseEntity<Void> updatePassord(@PathVariable Long userId, @RequestBody String password){
        updateUserGateway.updatePassword(userId, password);
        return ResponseEntity.noContent().build();
    }



}
