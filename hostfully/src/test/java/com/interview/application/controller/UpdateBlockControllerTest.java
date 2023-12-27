package com.interview.application.controller;

import com.interview.application.api.model.fixture.UpdateBlockApiRequestFixture;
import com.interview.application.api.model.fixture.mapper.HttpBodyMapper;
import com.interview.application.controller.api.model.request.UpdateBlockApiRequest;
import com.interview.application.domain.Block;
import com.interview.application.domain.fixture.BlockFixture;
import com.interview.application.usecase.UpdateBlockUseCase;
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

import java.util.HashMap;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateBlockController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class UpdateBlockControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private UpdateBlockUseCase updateBlockUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should update a block with success")
    public void shouldUpdateBlockWithSuccess() {
        //Given
        UUID id = UUID.randomUUID();
        Block block = BlockFixture.create();
        UpdateBlockApiRequest request = UpdateBlockApiRequestFixture.create(new HashMap<>());
        HttpBodyMapper<UpdateBlockApiRequest> httpBodyMapper = new HttpBodyMapper<>();

        //When
        when(updateBlockUseCase.execute(any(Block.class))).thenReturn(block);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .put("/blocks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request))
                )
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
        UpdateBlockApiRequest request = UpdateBlockApiRequestFixture.create(new HashMap<>());
        HttpBodyMapper<UpdateBlockApiRequest> httpBodyMapper = new HttpBodyMapper<>();

        //When
        when(updateBlockUseCase.execute(any(Block.class)))
                .thenThrow(new NotFoundUseCaseException("You can not update a non existent block"));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .put("/blocks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request))
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("You can not update a non existent block"))
                .andDo(print());
    }
}
