package com.lastminute.domain;

public class Profile {

    private Long id;
    private Person person;
    private Double muscleMassIndex;
    private java.time.LocalDateTime createdAt;

    public Profile(Person person) {
        this.muscleMassIndex = person.getWeight() / person.getHeight();
        this.createdAt = java.time.LocalDateTime.now();
    }

}
