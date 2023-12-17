package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString( of = { "id", "firstName", "lastName", "email" })
public class Booker {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
