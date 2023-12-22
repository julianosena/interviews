package com.interview.application.controller.api.config;

import com.interview.application.controller.api.exception.NotFoundRestApiResourceException;
import com.interview.application.controller.api.model.response.ErrorResponse;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {

    @SuppressWarnings("unchecked")
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotFoundRestApiResourceException(final MethodArgumentNotValidException e) {
        log.error("Method Argument Not valid exception", e);

        ErrorResponse response = new ErrorResponse();
        response.setMessage("Invalid request");
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String[] fieldParts = fieldError.getField().split("\\.");

            Map<String, Object> nestedErrors = response.getErrors();
            for (int i = 0; i < fieldParts.length - 1; i++) {
                String part = fieldParts[i];
                nestedErrors = (Map<String, Object>) nestedErrors.computeIfAbsent(part, value -> new HashMap<>());
            }

            String lastPart = fieldParts[fieldParts.length - 1];
            nestedErrors.put(lastPart, fieldError.getDefaultMessage());
        }

        return response;
    }

    @ExceptionHandler({UseCaseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUseCaseException(final UseCaseException e) {
        log.error("UseCase exception", e);
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({NotFoundRestApiResourceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotFoundRestApiResourceException(final NotFoundRestApiResourceException e) {
        log.error("UseCase exception", e);
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return response;
    }

}
