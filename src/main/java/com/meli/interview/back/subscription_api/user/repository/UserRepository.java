package com.meli.interview.back.subscription_api.user.repository;

import com.meli.interview.back.subscription_api.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("""
            SELECT u
            FROM UserEntity u
            JOIN u.friends f
            WHERE f.id = :userId
              AND u.email = :friendEmail
            """)
    Optional<UserEntity> getFriendByEmail(UUID userId, String friendEmail);

}
