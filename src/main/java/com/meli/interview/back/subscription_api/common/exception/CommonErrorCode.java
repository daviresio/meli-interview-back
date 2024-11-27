package com.meli.interview.back.subscription_api.common.exception;

import java.io.Serial;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum CommonErrorCode implements CommonError {
    ERROR_GENERAL_2_000(2_000, CommonHttpError.INTERNAL_SERVER_ERROR, "error.error-general-2000"),
    USER_ALREADY_EXIST(2_001, CommonHttpError.BAD_REQUEST, "error.user-already-exist-2001"),
    USER_INVALID(2_002, CommonHttpError.BAD_REQUEST, "error.user-invalid-2002"),
    SUBSCRIPTION_INVALID(2_003, CommonHttpError.BAD_REQUEST, "error.subscription-invalid-2003"),
    KEYCLOAK_CLIENT_ERROR(2_004, CommonHttpError.INTERNAL_SERVER_ERROR, "error.keycloak-client-error-2004"),
    USER_IS_NOT_FRIEND_ERROR(2_005, CommonHttpError.BAD_REQUEST, "error.user-is-not-friend-error-2005"),
    MISSING_ENV_VAR_ERROR(2_006, CommonHttpError.INTERNAL_SERVER_ERROR, "error.missing-env-var-error-2006");

    @Serial
    private static final long serialVersionUID = 1L;

    private final Properties properties;

    CommonErrorCode(int errorCode, CommonHttpError commonHttpError, String summary) {
        this.properties = Properties.of(errorCode, commonHttpError, summary);
    }
}