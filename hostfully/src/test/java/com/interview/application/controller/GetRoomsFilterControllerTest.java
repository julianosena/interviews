package com.interview.application.controller;

import com.interview.application.domain.Room;
import com.interview.application.domain.RoomFilter;
import com.interview.application.domain.fixture.HotelFixture;
import com.interview.application.domain.fixture.RoomFixture;
import com.interview.application.usecase.FindRoomsByFilterUseCase;
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
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetRoomsFilterController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class GetRoomsFilterControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private FindRoomsByFilterUseCase findRoomsByFilterUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should get list of rooms by filter with success")
    public void shouldGetListOfRoomsByFilterWithSuccess() {
        //Given
        Map<Object, Object> parameters = new HashMap<>();
        List<Room> rooms = RoomFixture.list(HotelFixture.create(), parameters);

        //When
        when(findRoomsByFilterUseCase.execute(any(RoomFilter.class))).thenReturn(rooms);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/rooms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].hotel").exists())
                .andExpect(jsonPath("$.[*].type").exists())
                .andExpect(jsonPath("$.[*].floor").exists())
                .andExpect(jsonPath("$.[*].number").exists())
                .andExpect(jsonPath("$.[*].maxOccupancy").exists())
                .andExpect(jsonPath("$.[*].available").isNotEmpty())
                .andExpect(jsonPath("$.[*].attributes").isNotEmpty())
                .andExpect(jsonPath("$.[*].createdAt").exists())
                .andExpect(jsonPath("$.[*].updatedAt").exists())
                .andDo(print());
    }
}
