package com.interview.application.controller.api.model;

import com.interview.application.domain.RoomAttribute;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class RoomApiModel {

    private UUID id;
    private HotelApiModel hotel;
    private RoomTypeApiModel type;
    private String floor;
    private String number;
    private Long maxOccupancy;
    private boolean available;
    private List<RoomAttribute> attributes;
    private Instant createdAt;
    private Instant updatedAt;
}
