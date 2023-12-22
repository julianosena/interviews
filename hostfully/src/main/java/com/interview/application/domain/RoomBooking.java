package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "booking", "room" })
public class RoomBooking {

    private Booking booking;
    private Room room;
    private String guestName;
    private String guestEmail;
    private Instant createdAt;
    private Instant updatedAt;

}
