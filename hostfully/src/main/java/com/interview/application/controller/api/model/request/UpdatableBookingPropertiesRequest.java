package com.interview.application.controller.api.model.request;

import com.interview.application.controller.api.validator.annotation.DateRange;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@DateRange(start = "checkinDate", end = "checkoutDate", message = "Checkin date must be before checkout date")
public class UpdatableBookingPropertiesRequest {

    @Future(message = "must be in the future")
    private LocalDate checkinDate;

    @Future(message = "must be in the future")
    private LocalDate checkoutDate;

    private List<@Valid UpdatableRoomBookingPropertiesRequest> roomBookings;
}