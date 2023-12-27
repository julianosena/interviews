package com.interview.application.controller;

import com.interview.application.usecase.DeleteBookingUseCase;
import com.interview.application.usecase.exception.NotFoundUseCaseException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeleteBookingController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class DeleteBookingControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private DeleteBookingUseCase deleteBookingUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should delete the booking with success")
    public void deleteBookingSuccessfully() {
        //Given
        UUID id = UUID.randomUUID();

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("It should return not found http bad request, because there is no booking with given id")
    public void shouldReturnNotFoundThereIsNoBookingWithGivenId() throws Exception {
        //Given
        UUID id = UUID.randomUUID();

        //When
        doThrow(new NotFoundUseCaseException("You can not delete a non existent booking")).when(deleteBookingUseCase).execute(any(UUID.class));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("You can not delete a non existent booking"))
                .andDo(print());
    }
}
