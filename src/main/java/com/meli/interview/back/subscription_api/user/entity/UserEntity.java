package com.meli.interview.back.subscription_api.user.entity;

import com.meli.interview.back.subscription_api.common.entity.CommonEntity;
import com.meli.interview.back.subscription_api.subscription.entity.SubscriptionEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity extends CommonEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column
    private String externalId;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private List<SubscriptionEntity> subscriptions = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<UserEntity> friends = new ArrayList<>();
}
