package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Hotel {

    private Long id;
    private String name;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
