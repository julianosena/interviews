package com.interview.application.controller.api.exception;

public class NotFoundRestApiResourceException extends RuntimeException {
    public NotFoundRestApiResourceException() {
    }

    public NotFoundRestApiResourceException(String message) {
        super(message);
    }

    public NotFoundRestApiResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundRestApiResourceException(Throwable cause) {
        super(cause);
    }

    public NotFoundRestApiResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
