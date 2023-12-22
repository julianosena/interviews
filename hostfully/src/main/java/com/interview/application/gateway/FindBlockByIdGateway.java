package com.interview.application.gateway;

import com.interview.application.domain.Block;

import java.util.Optional;
import java.util.UUID;

public interface FindBlockByIdGateway {

    Optional<Block> execute(UUID id);

}
