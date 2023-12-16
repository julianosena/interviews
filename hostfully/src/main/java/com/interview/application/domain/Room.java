package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class Room {

    private Long id;
    private Hotel hotel;
    private RoomType type;
    private String floor;
    private String number;
    private Long maxOccupancy;
    private Boolean isAvailable;
    private List<RoomAttribute> attributes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
