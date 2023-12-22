package com.interview.application.gateway.database.h2.model.mapper;

import com.interview.application.domain.Block;
import com.interview.application.gateway.database.h2.model.BlockH2;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockH2Mapper {

    BlockH2 map(Block block);

    Block map(BlockH2 block);

}
