package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Block {

    private UUID id;
    private LocalDate start;
    private LocalDate end;
    private Instant createdAt;
    private Instant updatedAt;

}
