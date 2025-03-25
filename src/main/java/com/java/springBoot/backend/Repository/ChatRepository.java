package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
