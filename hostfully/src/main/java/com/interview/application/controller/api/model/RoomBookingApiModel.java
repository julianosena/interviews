package com.interview.application.controller.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class RoomBookingApiModel {

    private RoomApiModel room;
    private String guestName;
    private String guestEmail;
    private Instant createdAt;
    private Instant updatedAt;

}
