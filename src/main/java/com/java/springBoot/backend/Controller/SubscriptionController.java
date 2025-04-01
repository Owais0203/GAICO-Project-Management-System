package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Model.PlanType;
import com.java.springBoot.backend.Model.Subscription;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Service.SubscriptionService;
import com.java.springBoot.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(@RequestHeader("Authorization") String jwt) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.getUsersSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(@RequestHeader("Authorization") String jwt, @RequestParam PlanType planType) throws Exception {
        Users user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
