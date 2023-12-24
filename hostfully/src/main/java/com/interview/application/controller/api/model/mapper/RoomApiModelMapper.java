package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.RoomApiModel;
import com.interview.application.domain.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomApiModelMapper {

    RoomApiModel map(Room room);

}
