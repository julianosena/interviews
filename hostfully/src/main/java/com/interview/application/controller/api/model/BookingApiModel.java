package com.interview.application.controller.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BookingApiModel {

    private UUID id;
    private BookerApiModel booker;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Long numberOfAdults;
    private Long numberOfChildren;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoomBookingApiModel> roomBookings;

    @Setter(AccessLevel.NONE)
    private BigDecimal totalAmount;

    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
    private BookingApiModel previousBooking;

    public enum Status {
        PENDING, CANCELED, PAID, REFUNDED
    }
}