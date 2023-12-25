package com.interview.application.controller;

import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.controller.api.model.mapper.BookingApiModelMapper;
import com.interview.application.controller.api.model.mapper.UpdatableBookingPropertiesRequestMapper;
import com.interview.application.controller.api.model.request.UpdatableBookingPropertiesRequest;
import com.interview.application.domain.Booking;
import com.interview.application.domain.UpdatableBookingProperties;
import com.interview.application.usecase.UpdateBookingUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management API", description = "This API provides endpoints to manage bookings.")
public class UpdateBookingDatesAndGuestDetailsController {

    private final UpdateBookingUseCase useCase;
    private final UpdatableBookingPropertiesRequestMapper updatableBookingPropertiesRequestMapper;
    private final BookingApiModelMapper bookingApiModelMapper;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingApiModel execute(@PathVariable("id") final UUID id, @RequestBody@Valid final UpdatableBookingPropertiesRequest request){
        UpdatableBookingProperties updatableBookingProperties = updatableBookingPropertiesRequestMapper.map(request);
        Booking booking = useCase.execute(id, updatableBookingProperties);
        return bookingApiModelMapper.map(booking);
    }
}