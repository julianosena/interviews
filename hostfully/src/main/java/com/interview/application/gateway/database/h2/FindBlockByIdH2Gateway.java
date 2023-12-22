package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Block;
import com.interview.application.gateway.FindBlockByIdGateway;
import com.interview.application.gateway.database.h2.model.mapper.BlockH2Mapper;
import com.interview.application.gateway.database.h2.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindBlockByIdH2Gateway implements FindBlockByIdGateway {

    private final BlockRepository repository;
    private final BlockH2Mapper mapper;

    @Override
    public Optional<Block> execute(UUID id) {
        return repository.findById(id).map(mapper::map);
    }

}
