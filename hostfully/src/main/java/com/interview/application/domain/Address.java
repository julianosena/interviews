package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Address {

    private UUID id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Instant createdAt;
    private Instant updatedAt;
    
}
