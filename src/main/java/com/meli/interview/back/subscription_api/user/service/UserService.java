package com.meli.interview.back.subscription_api.user.service;

import com.meli.interview.back.subscription_api.client.keycloak.service.KeycloakService;
import com.meli.interview.back.subscription_api.common.exception.CommonErrorCode;
import com.meli.interview.back.subscription_api.common.exception.CommonThrowable;
import com.meli.interview.back.subscription_api.user.resource.mapper.UserMapper;
import com.meli.interview.back.subscription_api.user.dao.UserDAO;
import com.meli.interview.back.subscription_api.user.entity.UserEntity;
import com.meli.interview.back.subscription_api.user.repository.UserRepository;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final KeycloakService keycloakService;
    private final UserMapper userMapper;

    public UserService(
            UserRepository userRepository,
            KeycloakService keycloakService,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.keycloakService = keycloakService;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserEntity createUser(UserDAO userDAO) {
        logger.info("Creating user with email: {}", userDAO.getEmail());

        try {

            UserEntity userEntity = userMapper.map(userDAO);
            UserEntity savedUser = userRepository.saveAndFlush(userEntity);

            logger.info("User saved in database with ID: {}", savedUser.getId());

            UserRepresentation userRepresentation = keycloakService.buildUserRepresentation(
                    userDAO.getEmail(),
                    userDAO.getPassword(),
                    savedUser.getId()
            );

            keycloakService.createUserInKeycloak(userRepresentation);
            logger.info("User created in Keycloak for email: {}", userDAO.getEmail());

            String keycloakUserId = keycloakService.getUserIdByUsername(userDAO.getEmail());
            logger.info("Keycloak user ID retrieved: {}", keycloakUserId);

            UserEntity userUpdated = userRepository.save(savedUser.toBuilder().externalId(keycloakUserId).build());

            logger.info("User creation process completed successfully for email: {}", userUpdated.getEmail());

            return userUpdated;

        } catch (DataIntegrityViolationException e) {
            logger.error("Error creating user in database for email: {}", userDAO.getEmail(), e);
            throw new CommonThrowable(CommonErrorCode.USER_ALREADY_EXIST, e);
        } catch (Exception e) {
            logger.error("Error creating user in Keycloak for email: {}", userDAO.getEmail(), e);
            throw new CommonThrowable(CommonErrorCode.ERROR_GENERAL_2_000, e);
        }

    }

    public Optional<UserEntity> getFriendByEmail(UUID userId, String friendEmail) {
        return userRepository.getFriendByEmail(userId, friendEmail);
    }
}
