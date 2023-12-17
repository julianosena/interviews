package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString( of = { "id", "name" })
public class Hotel {

    private UUID id;
    private String name;
    private Address address;
    private List<Room> rooms;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
