package com.buccodev.app_register.infrastructure.services.usecases.user_imp;

import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.db.UserDomainRepository;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CreateUserUsecaseImplTest {

    @Mock
    private UserDomainRepository repository;

    @InjectMocks
    private CreateUserUsecaseImpl usecaseService;

    @Nested
    class saveUser{

        @Test
        @DisplayName("Should save a user with success")
        void shouldSaveUserWithSuccess(){

            var userDomain = new UserDomain(10L, "Fabricio", LocalDate.of(1988, 11, 7),
                    "longobucco@gmail.com", "192839901", true );

            doReturn(userDomain).when(repository).save(any());

            var input = new User(null, "Fabricio", LocalDate.of(1988, 11, 7),
                    "longobucco@gmail.com", "192839901", true );

           var output = usecaseService.saveUser(input);

            assertNotNull(output);
        }
    }

}