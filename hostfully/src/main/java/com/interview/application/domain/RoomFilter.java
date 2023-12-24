package com.interview.application.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomFilter {

    private Boolean isAvailable;
    private Integer maxOccupancy;

}
