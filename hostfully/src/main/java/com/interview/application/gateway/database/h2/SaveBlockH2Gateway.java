package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Block;
import com.interview.application.gateway.SaveBlockGateway;
import com.interview.application.gateway.database.h2.model.BlockH2;
import com.interview.application.gateway.database.h2.model.mapper.BlockH2Mapper;
import com.interview.application.gateway.database.h2.repository.BlockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveBlockH2Gateway implements SaveBlockGateway {

    private final BlockRepository blockRepository;
    private final BlockH2Mapper blockH2Mapper;

    @Override
    @Transactional
    public Block execute(final Block block) {
        final BlockH2 mapped = blockH2Mapper.map(block);
        final BlockH2 persisted = blockRepository.saveAndFlush(mapped);
        return blockH2Mapper.map(persisted);
    }

}
