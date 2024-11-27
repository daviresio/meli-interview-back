package com.meli.interview.back.subscription_api.client.keycloak.service;

import com.meli.interview.back.subscription_api.config.EnvConfig;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class KeycloakService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakService.class);

    private final Keycloak keycloak;
    private final EnvConfig envConfig;

    public KeycloakService(Keycloak keycloak, EnvConfig envConfig) {
        this.keycloak = keycloak;
        this.envConfig = envConfig;
    }

    public void createUserInKeycloak(UserRepresentation userRepresentation) {
        logger.info("Creating user in Keycloak with email: {}", userRepresentation.getEmail());

        try (Response response = keycloak.realm(envConfig.getKeycloakRealm()).users().create(userRepresentation)) {
            logger.info("User created successfully in Keycloak for email: {}", userRepresentation.getEmail());
        } catch (Exception e) {
            logger.error("Error creating user in Keycloak for email: {}", userRepresentation.getEmail(), e);
            throw new RuntimeException("Error creating user in Keycloak", e);
        }
    }

    public UserRepresentation buildUserRepresentation(String email, String password, UUID userId) {
        logger.info("Building Keycloak user representation for email: {}", email);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(email);
        userRepresentation.setEmail(email);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setAttributes(Collections.singletonMap("X-User-Id", Collections.singletonList(userId.toString())));
        userRepresentation.setCredentials(Collections.singletonList(credential));

        logger.info("User representation built successfully for email: {}", email);
        return userRepresentation;
    }

    public String getUserIdByUsername(String username) {
        logger.info("Fetching Keycloak user ID for username: {}", username);

        try {
            UsersResource usersResource = keycloak.realm(envConfig.getKeycloakRealm()).users();
            List<UserRepresentation> users = usersResource.search(username, 0, 1);

            if (users.isEmpty()) {
                logger.warn("User not found in Keycloak for username: {}", username);
                throw new RuntimeException("User not found in Keycloak: " + username);
            }

            String userId = users.get(0).getId();
            logger.info("User ID retrieved successfully for username: {}: {}", username, userId);
            return userId;
        } catch (Exception e) {
            logger.error("Error fetching user ID from Keycloak for username: {}", username, e);
            throw new RuntimeException("Error fetching user ID from Keycloak", e);
        }
    }
}
