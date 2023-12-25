package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomFilter {

    private Boolean isAvailable;
    private Integer maxOccupancy;

}
