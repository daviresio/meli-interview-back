package com.meli.interview.back.subscription_api.user.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDAO {

    private String name;

    private String password;

    private String email;

    private List<UUID> subscriptionIds;

    private List<UUID> friendIds;
}
