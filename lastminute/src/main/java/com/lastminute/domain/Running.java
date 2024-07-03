package com.lastminute.domain;

public class Running {

    private java.util.UUID id;
    private final Activity activity;
    private Double kilometers;

    public Running(Person person){
        this.activity = new Activity(person);
    }

    public java.util.UUID getId(){
        return this.id;
    }

    public void setId(java.util.UUID uuid){
        this.id = uuid;
    }

}
