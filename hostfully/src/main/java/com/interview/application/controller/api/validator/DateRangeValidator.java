package com.interview.application.controller.api.validator;

import com.interview.application.controller.api.validator.annotation.DateRange;
import com.interview.application.controller.api.validator.exception.ConstraintValidatorException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {

    private String start;
    private String end;
    private String message;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        start = constraintAnnotation.start();
        end = constraintAnnotation.end();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            Field startDateField = value.getClass().getDeclaredField(start);
            startDateField.setAccessible(true);
            LocalDate startLocalDate = (LocalDate) startDateField.get(value);

            Field endDateField = value.getClass().getDeclaredField(end);
            endDateField.setAccessible(true);
            LocalDate endLocalDate = (LocalDate) endDateField.get(value);

            if (startLocalDate != null && endLocalDate != null && startLocalDate.isBefore(endLocalDate)) {
                return true;
            } else {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(start)
                        .addConstraintViolation();
                return false;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new ConstraintValidatorException("Wrong configuration, make sure the fields you declared in start and end parameters exist within the class");
        }
    }
}
