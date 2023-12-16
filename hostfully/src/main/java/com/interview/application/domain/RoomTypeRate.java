package com.interview.application.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode( of = { "id", "date" })
public class RoomTypeRate {

    private Long id;
    private LocalDate date;
    private BigDecimal rateAdult;
    private String rateChildren;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
