package com.interview.application.controller;

import com.interview.application.usecase.DeleteBlockUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("blocks")
@RequiredArgsConstructor
@Tag(name = "Block Management API", description = "This API provides endpoints to perform CRUD operations on blocks.")
public class DeleteBlockController {

    private final DeleteBlockUseCase useCase;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void execute(@PathVariable("id") final UUID id){
        useCase.execute(id);
    }
}
