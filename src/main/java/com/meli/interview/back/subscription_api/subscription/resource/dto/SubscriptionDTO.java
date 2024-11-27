package com.meli.interview.back.subscription_api.subscription.resource.dto;

import com.meli.interview.back.subscription_api.common.dto.CommonDto;
import com.meli.interview.back.subscription_api.subscription.type.Partner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class SubscriptionDTO extends CommonDto {

    private Partner partner;

    private float price;
}
