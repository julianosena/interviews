package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class UpdatableBookingProperties {

    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private List<UpdatableRoomBookingProperties> roomBookings;
}