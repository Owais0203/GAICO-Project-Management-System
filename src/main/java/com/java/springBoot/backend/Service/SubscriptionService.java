package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.PlanType;
import com.java.springBoot.backend.Model.Subscription;
import com.java.springBoot.backend.Model.Users;

public interface SubscriptionService {

    Subscription createSubscription(Users user) throws Exception;

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subscription) throws Exception;
}
