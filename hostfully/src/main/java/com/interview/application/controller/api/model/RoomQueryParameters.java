package com.interview.application.controller.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomQueryParameters {

    private Boolean available;
    private Integer maxOccupancy;

}
