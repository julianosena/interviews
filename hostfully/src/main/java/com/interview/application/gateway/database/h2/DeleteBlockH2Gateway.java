package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Block;
import com.interview.application.gateway.DeleteBlockGateway;
import com.interview.application.gateway.database.h2.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteBlockH2Gateway implements DeleteBlockGateway {

    private final BlockRepository repository;

    @Override
    public void execute(Block block) {
        repository.deleteById(block.getId());
    }

}
