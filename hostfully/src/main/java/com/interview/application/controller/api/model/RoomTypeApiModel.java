package com.interview.application.controller.api.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class RoomTypeApiModel {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal rateAdult;
    private BigDecimal rateChildren;
    private Instant createdAt;
    private Instant updatedAt;
}
