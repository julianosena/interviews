package com.interview.application.controller.api.model;

import com.interview.application.domain.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class HotelApiModel {

    private UUID id;
    private String name;
    private Address address;
    private List<RoomApiModel> rooms;
    private Instant createdAt;
    private Instant updatedAt;

}
