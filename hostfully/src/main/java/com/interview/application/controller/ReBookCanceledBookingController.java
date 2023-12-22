package com.interview.application.controller;

import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.controller.api.model.mapper.BookingApiModelMapper;
import com.interview.application.domain.Booking;
import com.interview.application.usecase.ReBookingCanceledBookingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("re-bookings")
@RequiredArgsConstructor
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