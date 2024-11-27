package com.meli.interview.back.subscription_api.subscription.resource.dto;

import com.meli.interview.back.subscription_api.subscription.type.Partner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CreateSubscriptionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Partner partner;

    private float price;
}
