package com.interview.application.controller.api.model.request;

import com.interview.application.controller.api.validator.annotation.DateRange;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@DateRange(start = "start", end = "end", message = "Start date must be before end")
public class UpdateBlockApiRequest {

    @NotNull(message = "must be informed")
    @FutureOrPresent(message = "must be today or in the future")
    private LocalDate start;

    @NotNull(message = "must be informed")
    @FutureOrPresent(message = "must be today or in the future")
    private LocalDate end;

}
