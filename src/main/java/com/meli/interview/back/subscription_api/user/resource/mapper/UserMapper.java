package com.meli.interview.back.subscription_api.user.resource.mapper;

import com.meli.interview.back.subscription_api.subscription.entity.SubscriptionEntity;
import com.meli.interview.back.subscription_api.subscription.resource.mapper.SubscriptionMapper;
import com.meli.interview.back.subscription_api.user.resource.dto.CreateUserDTO;
import com.meli.interview.back.subscription_api.user.dao.UserDAO;
import com.meli.interview.back.subscription_api.user.entity.UserEntity;
import com.meli.interview.back.subscription_api.user.resource.dto.PartialUpdateUserDTO;
import com.meli.interview.back.subscription_api.user.resource.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity map(UserDAO source) {
        return UserEntity.builder()
                .email(source.getEmail())
                .name(source.getName())
                .subscriptions(
                        Optional.ofNullable(source.getSubscriptionIds())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(subscriptionId -> SubscriptionEntity.builder().id(subscriptionId).build())
                                .collect(Collectors.toList())
                )
                .friends(
                        Optional.ofNullable(source.getFriendIds())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(friendId -> UserEntity.builder().id(friendId).build())
                                .collect(Collectors.toList())
                )
                .build();
    }

    public UserDAO map(CreateUserDTO source) {
        return UserDAO.builder()
                .name(source.getName())
                .email(source.getEmail())
                .password(source.getPassword())
                .subscriptionIds(source.getSubscriptionIds())
                .friendIds(source.getFriendIds())
                .build();
    }

    public UserDTO map(UserEntity source) {
        return UserDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .email(source.getEmail())
                .subscriptions(
                        Optional.ofNullable(source.getSubscriptions())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(v -> new SubscriptionMapper().map(v))
                                .collect(Collectors.toList())
                )
                .friendIds(
                        Optional.ofNullable(source.getFriends())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(UserEntity::getId)
                                .collect(Collectors.toList())
                )
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .build();
    }

    public UserEntity map(PartialUpdateUserDTO source) {
        return UserEntity.builder()
                .name(source.getName())
                .subscriptions(
                        Optional.ofNullable(source.getSubscriptionIds())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(subscriptionId -> SubscriptionEntity.builder().id(subscriptionId).build())
                                .collect(Collectors.toList())
                )
                .friends(
                        Optional.ofNullable(source.getFriendIds())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(friendId -> UserEntity.builder().id(friendId).build())
                                .collect(Collectors.toList())
                )
                .build();
    }


}
