package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class RoomAttribute {

    private Long id;
    private String description;

}
