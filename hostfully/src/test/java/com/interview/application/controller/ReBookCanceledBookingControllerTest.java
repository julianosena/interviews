package com.interview.application.controller;

import com.interview.application.domain.Booking;
import com.interview.application.domain.fixture.BookingFixture;
import com.interview.application.usecase.ReBookingCanceledBookingUseCase;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReBookCanceledBookingController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class ReBookCanceledBookingControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private ReBookingCanceledBookingUseCase reBookingCanceledBookingUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should re-book canceled booking with success")
    public void shouldReBookCanceledBookingSuccessfully() {
        //Given
        UUID id = UUID.randomUUID();
        Booking booking = BookingFixture.create();

        //When
        when(reBookingCanceledBookingUseCase.execute(any(UUID.class))).thenReturn(booking);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/re-bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.booker").exists())
                .andExpect(jsonPath("$.checkinDate").exists())
                .andExpect(jsonPath("$.checkoutDate").exists())
                .andExpect(jsonPath("$.numberOfAdults").exists())
                .andExpect(jsonPath("$.numberOfChildren").exists())
                .andExpect(jsonPath("$.roomBookings").isNotEmpty())
                .andExpect(jsonPath("$.totalAmount").exists())
                .andExpect(jsonPath("$.status").isNotEmpty())
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.updatedAt").exists())
                .andExpect(jsonPath("$.previousBooking").doesNotExist())
                .andDo(print());
    }

    @Test
    @DisplayName("It should return not found http bad request, because there is no booking with given id")
    public void shouldReturnNotFoundThereIsNoBookingWithGivenId() throws Exception {
        //Given
        UUID id = UUID.randomUUID();

        //When
        doThrow(new NotFoundUseCaseException("You can not re-book a non existent canceled booking")).when(reBookingCanceledBookingUseCase).execute(any(UUID.class));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/re-bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("You can not re-book a non existent canceled booking"))
                .andDo(print());
    }
}
