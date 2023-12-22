package com.interview.application.domain.mapper;

import com.interview.application.domain.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReCreateRoomBookingMapper {

    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RoomBooking map(RoomBooking RoomBooking);

}
