package com.interview.application.controller;

import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.controller.api.model.mapper.BookingApiModelMapper;
import com.interview.application.controller.api.model.request.CreateBookingRequest;
import com.interview.application.controller.api.model.translator.CreateBookingRequestToBookingTranslator;
import com.interview.application.domain.Booking;
import com.interview.application.usecase.CreateBookingUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
public class CreateBookingController {

    private final CreateBookingUseCase useCase;
    private final BookingApiModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingApiModel execute(@RequestBody @Valid final CreateBookingRequest request){
        Booking booking = CreateBookingRequestToBookingTranslator.translate(request);
        booking = useCase.execute(booking);
        return mapper.map(booking);
    }
}