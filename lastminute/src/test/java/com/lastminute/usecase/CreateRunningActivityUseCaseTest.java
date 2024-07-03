package com.lastminute.usecase;

public class CreateRunningActivityUseCaseTest {

    CreateRunningActivityUseCase useCase = new com.lastminute.usecase.CreateRunningActivityUseCase();


    @org.junit.jupiter.api.Test
    public void isShouldStoreAnActivityWithSuccess(){
        //Given
        com.lastminute.domain.Person person = new com.lastminute.domain.Person();
        person.setName("Juliano");
        person.setSurname("Sena da Silva Carlos");
        person.setBirthday(java.time.LocalDate.of(1988, 10, 15));
        person.setHeight(1.88);
        person.setWeight(99.0);

        com.lastminute.domain.Running running = new com.lastminute.domain.Running(person);

        //When
        com.lastminute.domain.Running result = useCase.execute(running);

        //Then
        org.junit.jupiter.api.Assertions.assertNotNull(result, "The result should not be null");
        org.junit.jupiter.api.Assertions.assertNotNull(result.getId(), "The result should have an id");
    }

}
