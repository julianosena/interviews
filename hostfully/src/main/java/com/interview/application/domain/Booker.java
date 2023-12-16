package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Booker {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
