package com.interview.application.controller.api.validator.exception;

public class ConstraintValidatorException extends RuntimeException {
    public ConstraintValidatorException() {
    }

    public ConstraintValidatorException(String message) {
        super(message);
    }

    public ConstraintValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintValidatorException(Throwable cause) {
        super(cause);
    }

    public ConstraintValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
