package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.gateway.FindBlockByIdGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBlockByIdUseCase {

    private final FindBlockByIdGateway findBlockByIdGateway;

    public Optional<Block> execute(UUID id){
        return findBlockByIdGateway.execute(id);
    }

}
