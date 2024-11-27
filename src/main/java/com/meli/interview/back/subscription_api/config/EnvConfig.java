package com.meli.interview.back.subscription_api.config;

import com.meli.interview.back.subscription_api.common.exception.CommonErrorCode;
import com.meli.interview.back.subscription_api.common.exception.CommonThrowable;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EnvConfig {

    @Value("${KEYCLOAK_AUTH_SERVER_URL}")
    private String keycloakAuthServerUrl;

    @Value("${KEYCLOAK_REALM}")
    private String keycloakRealm;

    @Value("${KEYCLOAK_RESOURCE}")
    private String keycloakResource;

    @Value("${KEYCLOAK_SECRET}")
    private String keycloakSecret;

    @Value("${KEYCLOAK_ADMIN_USERNAME}")
    private String keycloakAdminUsername;

    @Value("${KEYCLOAK_ADMIN_PASSWORD}")
    private String keycloakAdminPassword;

    @Value("${SPRING_DATASOURCE_URL}")
    private String datasourceUrl;

    @PostConstruct
    public void validateEnvVars() {
        validateEnvVar(keycloakAuthServerUrl, "KEYCLOAK_AUTH_SERVER_URL");
        validateEnvVar(keycloakRealm, "KEYCLOAK_REALM");
        validateEnvVar(keycloakResource, "KEYCLOAK_RESOURCE");
        validateEnvVar(keycloakSecret, "KEYCLOAK_SECRET");
        validateEnvVar(keycloakAdminUsername, "KEYCLOAK_ADMIN_USERNAME");
        validateEnvVar(keycloakAdminPassword, "KEYCLOAK_ADMIN_PASSWORD");
        validateEnvVar(datasourceUrl, "SPRING_DATASOURCE_URL");
    }

    private void validateEnvVar(String value, String varName) {
        if (value == null || value.isEmpty()) {
            throw new CommonThrowable(CommonErrorCode.MISSING_ENV_VAR_ERROR);
        }
    }

}
