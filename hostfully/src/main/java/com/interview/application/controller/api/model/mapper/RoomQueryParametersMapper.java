package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.RoomQueryParameters;
import com.interview.application.domain.RoomFilter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomQueryParametersMapper {

    RoomFilter map(RoomQueryParameters parameters);

}
