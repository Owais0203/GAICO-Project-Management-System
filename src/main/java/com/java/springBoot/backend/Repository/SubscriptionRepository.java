package com.java.springBoot.backend.Repository;

import com.java.springBoot.backend.Model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserId(Long userId);
}
