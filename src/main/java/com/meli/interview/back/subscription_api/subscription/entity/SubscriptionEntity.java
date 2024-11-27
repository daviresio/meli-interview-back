package com.meli.interview.back.subscription_api.subscription.entity;

import com.meli.interview.back.subscription_api.common.entity.CommonEntity;
import com.meli.interview.back.subscription_api.user.entity.UserEntity;
import com.meli.interview.back.subscription_api.subscription.type.Partner;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity extends CommonEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Partner partner;

    @Column(nullable = false)
    private float price;

    @ManyToMany(mappedBy = "subscriptions")
    @Builder.Default
    private List<UserEntity> users = new ArrayList<>();
}
