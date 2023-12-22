package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.BlockApiModel;
import com.interview.application.controller.api.model.request.CreateBlockApiRequest;
import com.interview.application.controller.api.model.request.UpdateBlockApiRequest;
import com.interview.application.domain.Block;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BlockApiModelMapper {

    BlockApiModel map(Block block);

    Block map(BlockApiModel block);

    Block map(CreateBlockApiRequest block);

    @Mapping(source = "id", target = "id")
    Block map(UUID id, UpdateBlockApiRequest request);

}
