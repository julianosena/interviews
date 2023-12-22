package com.interview.application.controller;

import com.interview.application.controller.api.model.BlockApiModel;
import com.interview.application.controller.api.model.mapper.BlockApiModelMapper;
import com.interview.application.controller.api.model.request.UpdateBlockApiRequest;
import com.interview.application.domain.Block;
import com.interview.application.usecase.UpdateBlockUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("blocks")
@RequiredArgsConstructor
@Tag(name = "Block Management API", description = "This API provides endpoints to perform CRUD operations on blocks.")
public class UpdateBlockController {

    private final UpdateBlockUseCase useCase;
    private final BlockApiModelMapper mapper;

    @PutMapping("/{id}")
    public BlockApiModel execute(@PathVariable("id") UUID id, @Valid @RequestBody UpdateBlockApiRequest request){
        final Block block = mapper.map(id, request);
        final Block updated = this.useCase.execute(block);
        return mapper.map(updated);
    }

}
