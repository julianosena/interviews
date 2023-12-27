package com.interview.application.controller;

import com.interview.application.domain.Block;
import com.interview.application.domain.fixture.BlockFixture;
import com.interview.application.usecase.FindBlockByIdUseCase;
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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetBlockByIdController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class GetBlockByIdControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private FindBlockByIdUseCase findBlockByIdUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should get a block by id with success")
    public void deleteBookingSuccessfully() {
        //Given
        UUID id = UUID.randomUUID();
        Block block = BlockFixture.create();

        //When
        when(findBlockByIdUseCase.execute(any(UUID.class))).thenReturn(Optional.of(block));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/blocks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(block.getId().toString()))
                .andExpect(jsonPath("$.start").value(block.getStart().toString()))
                .andExpect(jsonPath("$.end").value(block.getEnd().toString()))
                .andExpect(jsonPath("$.createdAt").value(block.getCreatedAt().toString()))
                .andExpect(jsonPath("$.updatedAt").value(block.getUpdatedAt().toString()))
                .andDo(print());
    }

    @Test
    @DisplayName("It should return not found http bad request, because there is no block with given id")
    public void shouldReturnNotFoundThereIsNoBlockWithGivenId() throws Exception {
        //Given
        UUID id = UUID.randomUUID();

        //When
        when(findBlockByIdUseCase.execute(any(UUID.class))).thenReturn(Optional.empty());

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/blocks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("There is no block with the given id"))
                .andDo(print());
    }
}
