package com.lastminute.domain;

public class Biceps {

    private final Activity activity;
    private Double weight;
    private Integer repetition;

    public Biceps(Person person){
        this.activity = new Activity(person);
    }

}
