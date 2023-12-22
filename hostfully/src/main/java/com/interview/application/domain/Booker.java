package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Booker {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Instant createdAt;
    private Instant updatedAt;

}
