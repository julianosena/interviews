package com.interview.application.controller;

import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.controller.api.model.mapper.BookingApiModelMapper;
import com.interview.application.domain.Booking;
import com.interview.application.usecase.CancelBookingUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management API", description = "This API provides endpoints to manage bookings.")
public class CancelBookingController {

    private final CancelBookingUseCase useCase;
    private final BookingApiModelMapper mapper;

    @PostMapping("/{id}/cancelation")
    @ResponseStatus(HttpStatus.OK)
    public BookingApiModel execute(@PathVariable("id") UUID id){
        Booking booking = useCase.execute(id);
        return mapper.map(booking);
    }
}