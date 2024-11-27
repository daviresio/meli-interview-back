package com.meli.interview.back.subscription_api.common.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serial;
import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@JsonSerialize(using = CommonErrorSerializer.class)
public interface CommonError extends Serializable {
    Properties getProperties();

    @Getter
    @ToString
    final class Properties implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        private final int errorCode;

        private final CommonHttpError commonHttpError;

        private final String summary;

        private Properties(int errorCode, CommonHttpError commonHttpError, String summary) {
            this.errorCode = errorCode;
            this.commonHttpError = commonHttpError;
            this.summary = summary;
        }

        public static Properties of(int errorCode, CommonHttpError commonHttpError, String summary) {
            return new Properties(errorCode, commonHttpError, summary);
        }
    }
}