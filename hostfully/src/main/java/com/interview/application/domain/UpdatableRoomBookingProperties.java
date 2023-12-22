package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = {"roomId", "bookingId"})
public class UpdatableRoomBookingProperties {

    private UUID roomId;
    private UUID bookingId;
    private String guestName;
    private String guestEmail;

}
