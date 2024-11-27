package com.meli.interview.back.subscription_api.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    private final EnvConfig envConfig;

    public KeycloakConfig(EnvConfig envConfig) {
        this.envConfig = envConfig;
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(envConfig.getKeycloakAuthServerUrl())
                .realm(envConfig.getKeycloakRealm())
                .clientId(envConfig.getKeycloakResource())
                .clientSecret(envConfig.getKeycloakSecret())
                .username(envConfig.getKeycloakAdminUsername())
                .password(envConfig.getKeycloakAdminPassword())
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }
}
