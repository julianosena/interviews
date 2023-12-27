package com.interview.application.controller;

import com.interview.application.domain.Booking;
import com.interview.application.domain.fixture.BookingFixture;
import com.interview.application.usecase.CancelBookingUseCase;
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
import java.util.Map;
import java.util.UUID;

import static com.interview.application.domain.Booking.Status.CANCELED;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CancelBookingController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class CancelBookingControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private CancelBookingUseCase cancelBookingUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should cancel the booking with success")
    public void cancelBookingSuccessfully() {
        //Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", CANCELED);
        Booking booking = (Booking) BookingFixture.create(parameters);

        //When
        when(cancelBookingUseCase.execute(booking.getId())).thenReturn(booking);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/bookings/{id}/cancelation", booking.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(CANCELED.name()))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    @DisplayName("It should return not found http bad request, because there is no booking with given id")
    public void cancelNonExistentBooking() {
        //Given
        UUID nonExistentBookingId = UUID.randomUUID();

        //When
        when(cancelBookingUseCase.execute(nonExistentBookingId))
                .thenThrow(new NotFoundUseCaseException("You can not cancel a non existent booking"));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/bookings/{id}/cancelation", nonExistentBookingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("You can not cancel a non existent booking"));
    }
}
