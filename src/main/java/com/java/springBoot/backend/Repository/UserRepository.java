package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String username);
}
