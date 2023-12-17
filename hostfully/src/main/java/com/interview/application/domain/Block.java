package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Block {

    private UUID id;
    private LocalDate start;
    private LocalDate end;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
