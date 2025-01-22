package com.buccodev.app_register.infrastructure.db;

import com.buccodev.app_register.infrastructure.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDomainRepository extends JpaRepository<UserDomain, Long> {
}
