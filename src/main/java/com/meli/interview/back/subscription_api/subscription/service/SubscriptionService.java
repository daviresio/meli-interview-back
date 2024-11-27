package com.meli.interview.back.subscription_api.subscription.service;

import com.meli.interview.back.subscription_api.common.exception.CommonErrorCode;
import com.meli.interview.back.subscription_api.common.exception.CommonThrowable;
import com.meli.interview.back.subscription_api.subscription.dao.SubscriptionCostDAO;
import com.meli.interview.back.subscription_api.subscription.entity.SubscriptionEntity;
import com.meli.interview.back.subscription_api.subscription.repository.SubscriptionRepository;
import com.meli.interview.back.subscription_api.user.entity.UserEntity;
import com.meli.interview.back.subscription_api.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
    }

    public SubscriptionEntity createSubscription(SubscriptionEntity subscription) {
        logger.info("Creating subscription for partner: {}, price: {}", subscription.getPartner(), subscription.getPrice());

        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscription);

        logger.info("Subscription created successfully with ID: {}", savedSubscription.getId());
        return savedSubscription;
    }

    public List<SubscriptionEntity> getSubscriptionsByUserId(UUID userId) {
        logger.info("Fetching subscriptions for user ID: {}", userId);

        List<SubscriptionEntity> subscriptions = subscriptionRepository.findSubscriptionsByUserId(userId);

        logger.info("Found {} subscriptions for user ID: {}", subscriptions.size(), userId);
        return subscriptions;
    }

    public SubscriptionCostDAO getTotalSubscriptionsCostIfFriend(UUID userId, String friendEmail) {
        logger.info("Calculating total subscription cost for user ID: {} if friend with email: {}", userId, friendEmail);

        Optional<UserEntity> friend = userService.getFriendByEmail(userId, friendEmail);

        return friend.map(friendEntity -> {
            logger.info("User ID: {} is a friend with email: {}", userId, friendEmail);

            Double totalCost = subscriptionRepository.getTotalSubscriptionsByUser(friendEntity.getId());

            logger.info("Total subscription cost for user ID: {} as a friend is: {}", userId, totalCost);

            return SubscriptionCostDAO.builder().cost(totalCost).build();
        }).orElseThrow(() -> {
            logger.warn("User ID: {} is not a friend with email: {}", userId, friendEmail);
            return new CommonThrowable(CommonErrorCode.USER_IS_NOT_FRIEND_ERROR);
        });

    }
}
