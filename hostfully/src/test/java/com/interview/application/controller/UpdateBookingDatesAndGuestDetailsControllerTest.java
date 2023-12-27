package com.interview.application.controller;

import com.interview.application.api.model.fixture.UpdatableBookingPropertiesRequestFixture;
import com.interview.application.api.model.fixture.mapper.HttpBodyMapper;
import com.interview.application.controller.api.model.request.UpdatableBookingPropertiesRequest;
import com.interview.application.domain.Booking;
import com.interview.application.domain.UpdatableBookingProperties;
import com.interview.application.domain.fixture.BookingFixture;
import com.interview.application.usecase.UpdateBookingUseCase;
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

@WebMvcTest(UpdateBookingDatesAndGuestDetailsController.class)
@ComponentScan(basePackages = {"com.interview.application.controller.api.model.mapper"})
public class UpdateBookingDatesAndGuestDetailsControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private UpdateBookingUseCase updateBookingUseCase;

    @Test
    @SneakyThrows
    @DisplayName("It should update a booking with success")
    public void shouldUpdateBookingWithSuccess() {
        //Given
        UUID id = UUID.randomUUID();
        Booking booking = BookingFixture.create();
        UpdatableBookingPropertiesRequest request = UpdatableBookingPropertiesRequestFixture.create(new HashMap<>());
        HttpBodyMapper<UpdatableBookingPropertiesRequest> httpBodyMapper = new HttpBodyMapper<>();

        //When
        when(updateBookingUseCase.execute(any(UUID.class), any(UpdatableBookingProperties.class))).thenReturn(booking);

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .put("/bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request))
                )
                .andExpect(status().isOk())
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
    @DisplayName("It should return not found http bad request, because there is no booking with given id")
    public void shouldReturnNotFoundThereIsNoBookingWithGivenId() throws Exception {
        //Given
        UUID id = UUID.randomUUID();
        UpdatableBookingPropertiesRequest request = UpdatableBookingPropertiesRequestFixture.create(new HashMap<>());
        HttpBodyMapper<UpdatableBookingPropertiesRequest> httpBodyMapper = new HttpBodyMapper<>();

        //When
        when(updateBookingUseCase.execute(any(UUID.class), any(UpdatableBookingProperties.class)))
                .thenThrow(new NotFoundUseCaseException("You can not update a non existent booking"));

        //Then
        mvc.perform(
                MockMvcRequestBuilders
                        .put("/bookings/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(httpBodyMapper.map(request))
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("You can not update a non existent booking"))
                .andDo(print());
    }
}
