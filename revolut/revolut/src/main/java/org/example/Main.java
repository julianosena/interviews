package org.example;

import java.util.*;

/**
 * Register instances
 *
 * It should be possible to register an instance, identified by an address
 *
 * Each address should be unique, it should not be possible to register the same address more than once
 *
 * Load Balancer should accept up to 10 addresses
 *
 * Develop an algorithm that, when invoking the Load Balancer 's get()
 * method multiple times, should return one backend-instance choosing
 * between the registered ones randomly.
 *
 */

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
}

class LoadBalancer {

    public final Map<String, Instance> instances = new java.util.HashMap<>();
    private final int LIMIT = 10;

    public synchronized void register(String address, Instance instance) {
        if(instances.containsKey(address)) {
            throw new IllegalArgumentException("Instance already registered: " + address);
        }

        if(instances.entrySet().size() == LIMIT){
            throw new org.example.exception.MaximumInstancesReachedException("We have already reached the maximum of instances");
        }

        instances.put(address, instance);
    }

    public synchronized Instance get(){
        int index = new java.util.Random().nextInt() % instances.size();
        if(index < 0) index *= -1;
        return (instances).entrySet().stream().toList().get(index).getValue();
    }
}

class Instance {

    final String address;

    public Instance(String address){
        this.address = address;
    }

}