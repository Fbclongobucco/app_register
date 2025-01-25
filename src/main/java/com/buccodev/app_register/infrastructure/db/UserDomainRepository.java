package com.buccodev.app_register.infrastructure.db;

import com.buccodev.app_register.core.entities.User;
import com.buccodev.app_register.infrastructure.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDomainRepository extends JpaRepository<UserDomain, Long> {

    boolean existsByEmail(String email);

    Optional<UserDomain> findByEmail(String email);

}
