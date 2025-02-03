package com.buccodev.app_register.infrastructure.config;

import com.buccodev.app_register.infrastructure.controllers.utils.TokerManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunConfiguration implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        TokerManager.generateAdminToken();
    }
}
