package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class RoomBooking {

    private UUID id;
    private Room room;
    private String guestName;
    private String guestEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
