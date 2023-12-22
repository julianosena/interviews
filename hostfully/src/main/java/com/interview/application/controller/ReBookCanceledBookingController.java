package com.interview.application.controller;

import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.controller.api.model.mapper.BookingApiModelMapper;
import com.interview.application.domain.Booking;
import com.interview.application.usecase.ReBookingCanceledBookingUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("re-bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management API", description = "This API provides endpoints to manage bookings.")
public class ReBookCanceledBookingController {

    private final ReBookingCanceledBookingUseCase useCase;
    private final BookingApiModelMapper mapper;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingApiModel execute(@PathVariable("id") UUID id){
        Booking booking = useCase.execute(id);
        return mapper.map(booking);
    }
}