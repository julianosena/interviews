package com.interview.application.controller;

import com.interview.application.api.model.fixture.BlockApiModelFixture;
import com.interview.application.api.model.fixture.mapper.HttpBodyMapper;
import com.interview.application.controller.api.model.BlockApiModel;
import com.interview.application.domain.Block;
import com.interview.application.domain.fixture.BlockFixture;
import com.interview.application.usecase.CreateBlockUseCase;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreateBlockController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class CreateBlockControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private CreateBlockUseCase createBlockUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should creates a block with success")
    public void shouldCreatesBlockWithSuccess() {
        //Given
        Block block = BlockFixture.create();
        BlockApiModel request = BlockApiModelFixture.create(new HashMap<>());
        HttpBodyMapper<BlockApiModel> httpBodyMapper = new HttpBodyMapper<>();

        //When
        when(createBlockUseCase.execute(any(Block.class))).thenReturn(block);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/blocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(block.getId().toString()))
                .andExpect(jsonPath("$.start").value(block.getStart().toString()))
                .andExpect(jsonPath("$.end").value(block.getEnd().toString()))
                .andExpect(jsonPath("$.createdAt").value(block.getCreatedAt().toString()))
                .andExpect(jsonPath("$.updatedAt").value(block.getUpdatedAt().toString()))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    @DisplayName("It should validates all required properties to create block")
    public void shouldValidatesAllRequiredPropertiesToCreateBlock() {
        //Given
        HashMap<Object, Object> parameters = new HashMap<>();
        parameters.put("start", null);
        parameters.put("end", null);

        //When
        BlockApiModel request = BlockApiModelFixture.create(parameters);
        HttpBodyMapper<BlockApiModel> httpBodyMapper = new HttpBodyMapper<>();

        //Then
        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/blocks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(httpBodyMapper.map(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Invalid request"))
                .andExpect(jsonPath("$.errors.start").value("must be informed"))
                .andExpect(jsonPath("$.errors.end").value("must be informed"))
                .andDo(print());
    }
}
