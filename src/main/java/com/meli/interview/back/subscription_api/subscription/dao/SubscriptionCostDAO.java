package com.meli.interview.back.subscription_api.subscription.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class SubscriptionCostDAO {

    private Double cost;

}
