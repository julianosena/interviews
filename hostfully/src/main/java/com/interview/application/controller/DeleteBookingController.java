package com.interview.application.controller;

import com.interview.application.usecase.DeleteBookingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
public class DeleteBookingController {

    private final DeleteBookingUseCase useCase;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void execute(@PathVariable("id") UUID id){
        useCase.execute(id);
    }
}