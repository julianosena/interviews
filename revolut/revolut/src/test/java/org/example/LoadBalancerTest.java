package org.example;

public class LoadBalancerTest {

    @org.junit.Test
    public void itShouldCheckUniqueIdentifierWithinLoadBalancerWithSuccess(){
        //Given
        LoadBalancer lb = new org.example.LoadBalancer();
        Instance one = new org.example.Instance("http://www.google.com");
        Instance two = new org.example.Instance("http://www.microsoft.com");
        Instance three = new org.example.Instance("http://www.google.com");

        try {
            //When
            lb.register(one.address, one);
            lb.register(two.address, two);
            lb.register(three.address, three);

            org.junit.Assert.fail("It is not allowed to register twice or more the same instance");

        } catch (IllegalArgumentException e){
            //Then
            org.junit.Assert.assertEquals("", "Instance already registered: http://www.google.com", e.getMessage());
        }
    }

    @org.junit.Test
    public void itShouldCheckIfItIsPossibleToRegisterMoreThanPossibleInstancesWithSuccess(){
        //Given
        LoadBalancer lb = new org.example.LoadBalancer();

        try {
            //When
            for(int i = 1 ; i < 12 ; i++){
                Instance instance = new org.example.Instance("instance" + i);
                lb.register(instance.address, instance);
            }

            org.junit.Assert.fail("It is not allowed to register more than 10 instances");

        } catch (org.example.exception.MaximumInstancesReachedException e){
            //Then
            org.junit.Assert.assertEquals("", "We have already reached the maximum of instances", e.getMessage());
        }
    }

    @org.junit.Test
    public void itShouldRegisterTheAllowedAmountOfInstancesWithSuccess(){
        //Given
        LoadBalancer lb = new org.example.LoadBalancer();

        try {
            //When
            for(int i = 1 ; i < 11 ; i++){
                Instance instance = new org.example.Instance("instance" + i);
                lb.register(instance.address, instance);
            }

            org.junit.Assert.assertEquals(10, lb.instances.size());

        } catch (Exception e){
            //Then
            org.junit.Assert.fail("It should not fail the test");
        }
    }

    @org.junit.Test
    public void itShouldReturnRandomInstanceFromLoadBalancer(){
        //Given
        LoadBalancer lb = new org.example.LoadBalancer();

        try {
            //When
            for (int i = 1; i < 11; i++) {
                Instance instance = new org.example.Instance("instance" + i);
                lb.register(instance.address, instance);
            }

            for (int i = 0; i < 10; i++) {
                Instance instance = lb.get();
                org.junit.Assert.assertNotNull(instance);
            }

        } catch (Exception e){
            //Then
            org.junit.Assert.fail("It should not fail the test");
        }
    }

}
