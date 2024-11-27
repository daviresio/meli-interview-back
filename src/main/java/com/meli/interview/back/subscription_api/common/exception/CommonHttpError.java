package com.meli.interview.back.subscription_api.common.exception;

import lombok.Getter;

@Getter
public enum CommonHttpError {
    /**
     * 500 - Internal Server Error
     * 400 - Bad Request
     * 401 - Unauthorized
     * 403 - Forbidden
     * 404 - Not Found
     * 412 - Precondition Failed
     */
    INTERNAL_SERVER_ERROR(500),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    PRECONDITION_FAILED(412);

    private final int httpErrorCode;

    CommonHttpError(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }
}