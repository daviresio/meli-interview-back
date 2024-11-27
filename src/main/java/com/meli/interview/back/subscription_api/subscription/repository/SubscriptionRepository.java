package com.meli.interview.back.subscription_api.subscription.repository;

import java.util.List;
import java.util.UUID;


import com.meli.interview.back.subscription_api.subscription.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

    @Query(value = """
                SELECT s.* 
                FROM subscriptions s
                JOIN user_subscriptions us ON s.id = us.subscription_id
                WHERE us.user_id = :userId
            """, nativeQuery = true)
    List<SubscriptionEntity> findSubscriptionsByUserId(UUID userId);


    @Query("""
            SELECT COALESCE(SUM(s.price), 0.0)
            FROM SubscriptionEntity s
            JOIN s.users u
            WHERE u.id = :userId
            """)
    Double getTotalSubscriptionsByUser(UUID userId);

}
