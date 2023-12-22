package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Hotel {

    private UUID id;
    private String name;
    private Address address;
    private List<Room> rooms;
    private Instant createdAt;
    private Instant updatedAt;

}
