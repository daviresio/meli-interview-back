package com.meli.interview.back.subscription_api.subscription.type;

import lombok.Getter;

@Getter
public enum Partner {
    DISNEY(100),
    NETFLIX(200),
    SPOTIFY(50);

    private final float price;

    Partner(float price) {
        this.price = price;
    }
}
