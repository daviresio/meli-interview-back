package com.meli.interview.back.subscription_api.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Arrays;

@ControllerAdvice
public class CommonHttpExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonHttpExceptionHandler.class);

    @ExceptionHandler(CommonThrowable.class)
    public ResponseEntity<CommonThrowable> handleCommonThrowable(CommonThrowable throwable) {
        logger.error("Error thrown: {} - {} - {}",
                throwable.getClass().getName(),
                throwable.getError().getProperties().getSummary(),
                throwable.getArgs());

        return new ResponseEntity<>(throwable,
                HttpStatus.valueOf(throwable.getError().getProperties().getCommonHttpError().getHttpErrorCode()));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommonThrowable> handleThrowable(Throwable throwable) {
        logger.error("Error thrown: {} - {}", throwable.getClass().getName(), throwable.getMessage());
        logger.error(Arrays.toString(throwable.getStackTrace()).replace(',', '\n'));

        CommonThrowable commonThrowable = new CommonThrowable(CommonErrorCode.ERROR_GENERAL_2_000, throwable);

        return new ResponseEntity<>(commonThrowable,
                HttpStatus.valueOf(commonThrowable.getError().getProperties().getCommonHttpError().getHttpErrorCode()));
    }
}
