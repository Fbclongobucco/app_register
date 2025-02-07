package com.buccodev.app_register.infrastructure.config;

import com.buccodev.app_register.infrastructure.controllers.utils.TokenManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunConfiguration implements CommandLineRunner {

    private final TokenManager tokenManager;

    public RunConfiguration(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void run(String... args) throws Exception {
        tokenManager.generateAdminToken();


        System.out.println("estou de pé!");

    }
}
