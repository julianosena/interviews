package com.interview.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
@ToString( of = {"id", "name", "description", "rateAdult", "rateChildren"} )
public class RoomType {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal rateAdult;
    private BigDecimal rateChildren;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
