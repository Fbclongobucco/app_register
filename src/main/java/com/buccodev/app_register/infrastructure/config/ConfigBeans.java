package com.buccodev.app_register.infrastructure.config;

import com.buccodev.app_register.application.gateway.CreateUserGateway;
import com.buccodev.app_register.application.gateway.DeleteUserGateway;
import com.buccodev.app_register.application.gateway.GetUserGateway;
import com.buccodev.app_register.application.gateway.UpdateUserGateway;
import com.buccodev.app_register.application.usecase.CreateUserUsecase;
import com.buccodev.app_register.application.usecase.DeleteUserUsecase;
import com.buccodev.app_register.application.usecase.GetUserUsecase;
import com.buccodev.app_register.application.usecase.UpdateUserUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {

    @Bean
    CreateUserGateway createUserGateway(CreateUserUsecase usecase){
        return new CreateUserGateway(usecase);
    }
    @Bean
    DeleteUserGateway deleteUserGateway(DeleteUserUsecase usecase){
        return new DeleteUserGateway(usecase);
    }
    @Bean
    GetUserGateway getUserGateway(GetUserUsecase usecase){
        return new GetUserGateway(usecase);
    }
    @Bean
    UpdateUserGateway updateUserGateway(UpdateUserUsecase usecase){
        return  new UpdateUserGateway(usecase);
    }

}
