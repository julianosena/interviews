package com.interview.application.controller;

import com.interview.application.api.model.fixture.CreateBookingRequestFixture;
import com.interview.application.api.model.fixture.mapper.HttpBodyMapper;
import com.interview.application.controller.api.model.request.CreateBookingRequest;
import com.interview.application.domain.Booking;
import com.interview.application.domain.fixture.BookingFixture;
import com.interview.application.usecase.CreateBookingUseCase;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreateBookingController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class CreateBookingControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private CreateBookingUseCase createBookingUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should creates a booking with success")
    public void shouldCreatesBookingWithSuccess() {
        //Given
        Booking booking = BookingFixture.create();
        CreateBookingRequest request = CreateBookingRequestFixture.create(new HashMap<>());
        HttpBodyMapper<CreateBookingRequest> httpBodyMapper = new HttpBodyMapper<>();

        //When
        when(createBookingUseCase.execute(any(Booking.class))).thenReturn(booking);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(booking.getId().toString()))
                .andExpect(jsonPath("$.booker").exists())
                .andExpect(jsonPath("$.checkinDate").exists())
                .andExpect(jsonPath("$.checkoutDate").exists())
                .andExpect(jsonPath("$.numberOfAdults").exists())
                .andExpect(jsonPath("$.numberOfChildren").exists())
                .andExpect(jsonPath("$.roomBookings").isNotEmpty())
                .andExpect(jsonPath("$.totalAmount").exists())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.createdAt").value(booking.getCreatedAt().toString()))
                .andExpect(jsonPath("$.updatedAt").value(booking.getUpdatedAt().toString()))
                .andExpect(jsonPath("$.previousBooking").doesNotExist())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    @DisplayName("It should validates all required properties to create booking")
    public void shouldValidatesAllRequiredPropertiesToCreateBooking() {
        //Given
        Map<String, String> request = new HashMap<>();
        HttpBodyMapper<Map<String, String>> httpBodyMapper = new HttpBodyMapper<>();

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Invalid request"))
                .andExpect(jsonPath("$.errors.booker").value("must be informed"))
                .andExpect(jsonPath("$.errors.checkinDate").value("must be informed"))
                .andExpect(jsonPath("$.errors.checkoutDate").value("must be informed"))
                .andExpect(jsonPath("$.errors.numberOfChildren").value("must be informed"))
                .andExpect(jsonPath("$.errors.roomBookings").value("must be informed"))
                .andDo(print());
    }
}
