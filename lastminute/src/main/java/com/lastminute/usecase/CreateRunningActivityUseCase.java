package com.lastminute.usecase;

public class CreateRunningActivityUseCase {

    private com.lastminute.gateway.CreateRunningActivityGateway gateway = new com.lastminute.gateway.database.CreateRunningActivityListGateway();

    public com.lastminute.domain.Running execute(com.lastminute.domain.Running running){
        return this.gateway.execute(running);
    }

}
