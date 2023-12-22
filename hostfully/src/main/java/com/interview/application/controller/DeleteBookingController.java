package com.interview.application.controller;

import com.interview.application.usecase.DeleteBookingUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management API", description = "This API provides endpoints to manage bookings.")
public class DeleteBookingController {

    private final DeleteBookingUseCase useCase;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void execute(@PathVariable("id") UUID id){
        useCase.execute(id);
    }
}