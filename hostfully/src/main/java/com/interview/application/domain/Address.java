package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Address {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    
}
