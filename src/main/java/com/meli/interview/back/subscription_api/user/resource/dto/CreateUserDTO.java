package com.meli.interview.back.subscription_api.user.resource.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CreateUserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private String password;

    private List<UUID> subscriptionIds;

    private List<UUID> friendIds;

}