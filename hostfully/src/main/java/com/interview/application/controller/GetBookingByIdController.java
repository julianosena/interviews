package com.interview.application.controller;

import com.interview.application.controller.api.exception.NotFoundRestApiResourceException;
import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.controller.api.model.mapper.BookingApiModelMapper;
import com.interview.application.usecase.FindBookingByIdUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management API", description = "This API provides endpoints to manage bookings.")
public class GetBookingByIdController {

    private final FindBookingByIdUseCase useCase;
    private final BookingApiModelMapper mapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingApiModel execute(@PathVariable("id") final UUID id){
        return useCase.execute(id).map(mapper::map)
                .orElseThrow(() -> new NotFoundRestApiResourceException("There is no booking with the given id"));
    }
}