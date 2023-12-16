package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id" })
public class RoomType {

    private Long id;
    private String name;
    private String description;
    private BigDecimal rateAdult;
    private BigDecimal rateChildren;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
