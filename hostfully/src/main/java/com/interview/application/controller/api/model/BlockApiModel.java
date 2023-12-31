package com.interview.application.controller.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BlockApiModel {

    private UUID id;
    private LocalDate start;
    private LocalDate end;
    private Instant createdAt;
    private Instant updatedAt;

}
