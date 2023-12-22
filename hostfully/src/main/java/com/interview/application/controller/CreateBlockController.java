package com.interview.application.controller;

import com.interview.application.controller.api.model.BlockApiModel;
import com.interview.application.controller.api.model.mapper.BlockApiModelMapper;
import com.interview.application.controller.api.model.request.CreateBlockApiRequest;
import com.interview.application.domain.Block;
import com.interview.application.usecase.CreateBlockUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blocks")
@RequiredArgsConstructor
public class CreateBlockController {

    private final CreateBlockUseCase useCase;
    private final BlockApiModelMapper mapper;

    @PostMapping
    public BlockApiModel execute(@Valid @RequestBody CreateBlockApiRequest request){
        final Block block = mapper.map(request);
        final Block updated = this.useCase.execute(block);
        return mapper.map(updated);
    }

}
