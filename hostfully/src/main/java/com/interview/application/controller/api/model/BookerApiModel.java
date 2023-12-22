package com.interview.application.controller.api.model;

import com.interview.application.domain.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BookerApiModel {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Instant createdAt;
    private Instant updatedAt;

}
