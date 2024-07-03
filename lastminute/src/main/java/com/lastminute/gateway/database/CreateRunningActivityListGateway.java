package com.lastminute.gateway.database;

public class CreateRunningActivityListGateway implements com.lastminute.gateway.CreateRunningActivityGateway {

    private final java.util.List<com.lastminute.domain.Running> list = new java.util.ArrayList<>();

    @Override
    public com.lastminute.domain.Running execute(com.lastminute.domain.Running running) {
        running.setId(java.util.UUID.randomUUID());
        list.add(running);
        return running;
    }

}
