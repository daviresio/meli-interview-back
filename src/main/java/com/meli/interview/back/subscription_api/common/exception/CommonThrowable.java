package com.meli.interview.back.subscription_api.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@JsonIgnoreProperties({"stackTrace", "suppressed", "localizedMessage"})
@JsonSerialize(using = CommonThrowableSerializer.class)
@JsonDeserialize(using = CommonThrowableDeserializer.class)
@ToString
public class CommonThrowable extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final CommonError error;

    private final UUID traceId;

    private final OffsetDateTime timestamp;

    private final Map<String, Object> args = new HashMap<>();

    private final String errorType;

    private final CommonThrowable cause;

    public CommonThrowable(CommonError error, Throwable cause, Map<String, Object> args) {
        super(error.getProperties().getSummary());
        this.error = error;
        this.timestamp = OffsetDateTime.now();
        this.getArgs().putAll(args);
        this.errorType = this.getClass().getName();
        if (cause instanceof CommonThrowable commonThrowable) {
            this.traceId = commonThrowable.getTraceId();
            this.cause = commonThrowable;
        } else {
            this.traceId = UUID.randomUUID();
            this.cause = new CommonThrowable(cause);
        }
    }

    public CommonThrowable(CommonError error, Throwable cause) {
        super(error.getProperties().getSummary());
        this.error = error;
        this.timestamp = OffsetDateTime.now();
        this.errorType = this.getClass().getName();
        if (cause instanceof CommonThrowable commonThrowable) {
            this.traceId = commonThrowable.getTraceId();
            this.cause = commonThrowable;
        } else {
            this.traceId = UUID.randomUUID();
            this.cause = new CommonThrowable(cause);
        }
    }

    public CommonThrowable(CommonError error) {
        super(error.getProperties().getSummary());
        this.error = error;
        this.traceId = UUID.randomUUID();
        this.timestamp = OffsetDateTime.now();
        this.errorType = this.getClass().getName();
        this.cause = null;
    }

    public CommonThrowable(CommonError error, Map<String, Object> args) {
        super(error.getProperties().getSummary());
        this.error = error;
        this.traceId = UUID.randomUUID();
        this.timestamp = OffsetDateTime.now();
        this.getArgs().putAll(args);
        this.errorType = this.getClass().getName();
        this.cause = null;
    }

    public CommonThrowable() {
        super();
        this.error = CommonErrorCode.ERROR_GENERAL_2_000;
        this.traceId = UUID.randomUUID();
        this.timestamp = OffsetDateTime.now();
        this.errorType = this.getClass().getName();
        this.cause = null;
    }

    public CommonThrowable(CommonError error, UUID traceId, OffsetDateTime timestamp, Map<String, Object> args, String errorType) {
        super(error.getProperties().getSummary());
        this.error = error;
        this.traceId = traceId;
        this.timestamp = timestamp;
        this.getArgs().putAll(args);
        this.errorType = errorType;
        this.cause = null;
    }

    public CommonThrowable(Throwable cause) {
        super(cause.getMessage());
        if (cause instanceof CommonThrowable commonThrowable) {
            this.error = commonThrowable.getError();
            this.timestamp = commonThrowable.getTimestamp();
            this.traceId = commonThrowable.getTraceId();
            this.errorType = commonThrowable.getErrorType();
            this.getArgs().putAll(commonThrowable.getArgs());
        } else {
            this.error = CommonErrorCode.ERROR_GENERAL_2_000;
            this.timestamp = OffsetDateTime.now();
            this.errorType = this.getClass().getName();
            this.traceId = UUID.randomUUID();
        }
        this.cause = null;
    }
}