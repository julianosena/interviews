package com.interview.application.domain.mapper;

import com.interview.application.domain.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReCreateRoomBookingMapper {

    @Mapping(target = "id", ignore = true)
    RoomBooking map(RoomBooking RoomBooking);

}
