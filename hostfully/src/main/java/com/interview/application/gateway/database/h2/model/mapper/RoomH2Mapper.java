package com.interview.application.gateway.database.h2.model.mapper;

import com.interview.application.domain.Room;
import com.interview.application.gateway.database.h2.model.RoomH2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoomH2Mapper {

    @Mapping(target = "hotel.rooms", ignore = true)
    Room map(RoomH2 room);

    @Mapping(target = "hotel.rooms", ignore = true)
    @Mapping(target = "roomBookings", ignore = true)
    RoomH2 map(Room room);
}
