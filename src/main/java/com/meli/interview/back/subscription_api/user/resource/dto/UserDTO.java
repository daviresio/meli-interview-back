package com.meli.interview.back.subscription_api.user.resource.dto;

import com.meli.interview.back.subscription_api.common.dto.CommonDto;
import com.meli.interview.back.subscription_api.subscription.resource.dto.SubscriptionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO extends CommonDto {

    private String name;

    private String email;

    private List<SubscriptionDTO> subscriptions;

    private List<UUID> friendIds;

}