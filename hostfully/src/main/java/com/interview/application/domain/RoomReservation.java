package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id", "room" })
public class RoomReservation {

    private Long id;
    private Room room;
    private String guestName;
    private String guestEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
