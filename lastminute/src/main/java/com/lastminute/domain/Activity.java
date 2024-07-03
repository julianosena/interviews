package com.lastminute.domain;

public class Activity {

    private final Person person;
    private java.time.LocalDateTime createdAt;
    private final java.time.LocalDateTime startTime;
    private java.time.LocalDateTime endTime;

    public Activity(Person person) {
        this.person = person;
        this.createdAt = java.time.LocalDateTime.now();
        this.startTime = java.time.LocalDateTime.now();
    }


}
