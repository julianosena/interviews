package com.interview.application.controller.api.model.request;

import com.interview.application.controller.api.validator.annotation.DateRange;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@DateRange(start = "start", end = "end", message = "Start date must be before")
public class CreateBlockApiRequest {

    @NotNull(message = "must be informed")
    private LocalDate start;

    @NotNull(message = "must be informed")
    private LocalDate end;

}
