package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class Room {

    private UUID id;
    private Hotel hotel;
    private RoomType type;
    private String floor;
    private String number;
    private Long maxOccupancy;
    private Boolean available;
    private List<RoomAttribute> attributes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", type=" + type +
                ", floor='" + floor + '\'' +
                ", number='" + number + '\'' +
                ", maxOccupancy=" + maxOccupancy +
                ", available=" + available +
                ", attributes=" + attributes +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
