package com.meli.interview.back.subscription_api.subscription.resource.mapper;

import com.meli.interview.back.subscription_api.subscription.dao.SubscriptionCostDAO;
import com.meli.interview.back.subscription_api.subscription.entity.SubscriptionEntity;
import com.meli.interview.back.subscription_api.subscription.resource.dto.CreateSubscriptionDTO;
import com.meli.interview.back.subscription_api.subscription.resource.dto.SubscriptionCostDTO;
import com.meli.interview.back.subscription_api.subscription.resource.dto.SubscriptionDTO;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscriptionEntity map(CreateSubscriptionDTO source) {

        return SubscriptionEntity.builder()
                .partner(source.getPartner())
                .price(source.getPrice())
                .build();
    }

    public SubscriptionDTO map(SubscriptionEntity source) {
        return SubscriptionDTO.builder()
                .id(source.getId())
                .partner(source.getPartner())
                .price(source.getPrice())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .build();
    }

    public SubscriptionCostDTO map(SubscriptionCostDAO source) {
        return SubscriptionCostDTO.builder()
                .cost(source.getCost())
                .build();
    }
}
