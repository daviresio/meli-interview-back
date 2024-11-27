package com.meli.interview.back.subscription_api.subscription.resource;

import com.meli.interview.back.subscription_api.subscription.dao.SubscriptionCostDAO;
import com.meli.interview.back.subscription_api.subscription.entity.SubscriptionEntity;
import com.meli.interview.back.subscription_api.subscription.resource.dto.CreateSubscriptionDTO;
import com.meli.interview.back.subscription_api.common.annotation.annotation.ExtractUserId;
import com.meli.interview.back.subscription_api.subscription.resource.dto.SubscriptionCostDTO;
import com.meli.interview.back.subscription_api.subscription.resource.dto.SubscriptionDTO;
import com.meli.interview.back.subscription_api.subscription.resource.mapper.SubscriptionMapper;
import com.meli.interview.back.subscription_api.subscription.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionResource {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionResource.class);

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionResource(SubscriptionService subscriptionService, SubscriptionMapper subscriptionMapper) {
        this.subscriptionService = subscriptionService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody CreateSubscriptionDTO createSubscriptionDTO) {
        logger.info("Received request to create subscription");

        SubscriptionEntity createdSubscription = subscriptionService.createSubscription(this.subscriptionMapper.map(createSubscriptionDTO));

        SubscriptionDTO response = this.subscriptionMapper.map(createdSubscription);

        logger.info("Subscription created successfully with ID: {}", createdSubscription.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getUserSubscriptions(@ExtractUserId UUID userId) {
        logger.info("Fetching subscriptions for user with ID: {}", userId);

        List<SubscriptionEntity> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);

        List<SubscriptionDTO> subscriptionsResponse = subscriptions.stream().map(subscriptionMapper::map).toList();

        logger.info("Retrieved {} subscriptions for user with ID: {}", subscriptions.size(), userId);

        return ResponseEntity.ok(subscriptionsResponse);
    }

    @GetMapping("/cost")
    public ResponseEntity<SubscriptionCostDTO> getTotalSubscriptionsCost(@ExtractUserId UUID userId, @RequestParam String friendEmail) {

        logger.info("Fetching total subscription cost for user with ID: {}", userId);

        if (friendEmail != null) {
            logger.info("Friend's email: {}", friendEmail);
        }

        SubscriptionCostDAO totalCost = subscriptionService.getTotalSubscriptionsCostIfFriend(userId, friendEmail);

        SubscriptionCostDTO response = this.subscriptionMapper.map(totalCost);

        logger.info("Total cost for user with ID: {} and friendEmail: {} is {}", userId, friendEmail, totalCost);

        return ResponseEntity.ok(response);
    }
}
