package com.interview.application.controller;

import com.interview.application.controller.api.exception.NotFoundRestApiResourceException;
import com.interview.application.controller.api.model.BlockApiModel;
import com.interview.application.controller.api.model.mapper.BlockApiModelMapper;
import com.interview.application.usecase.FindBlockByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("blocks")
@RequiredArgsConstructor
public class GetBlockByIdController {

    private final FindBlockByIdUseCase useCase;
    private final BlockApiModelMapper mapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlockApiModel execute(@PathVariable("id") UUID id){
        return useCase.execute(id).map(mapper::map)
                .orElseThrow(() -> new NotFoundRestApiResourceException("There is no block for the given id"));
    }
}