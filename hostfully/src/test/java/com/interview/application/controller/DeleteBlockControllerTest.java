package com.interview.application.controller;

import com.interview.application.usecase.DeleteBlockUseCase;
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

@WebMvcTest(DeleteBlockController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class DeleteBlockControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private DeleteBlockUseCase deleteBlockUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should delete the block with success")
    public void deleteBlockSuccessfully() {
        //Given
        UUID id = UUID.randomUUID();

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/blocks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("It should return not found http bad request, because there is no block with given id")
    public void shouldReturnNotFoundThereIsNoBookingWithGivenId() throws Exception {
        //Given
        UUID id = UUID.randomUUID();

        //When
        doThrow(new NotFoundUseCaseException("You can not delete a non existent block")).when(deleteBlockUseCase).execute(any(UUID.class));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/blocks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("You can not delete a non existent block"))
                .andDo(print());
    }
}
