package com.meli.interview.back.subscription_api.user.resource;

import com.meli.interview.back.subscription_api.subscription.service.SubscriptionService;
import com.meli.interview.back.subscription_api.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public UserResource(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

}
