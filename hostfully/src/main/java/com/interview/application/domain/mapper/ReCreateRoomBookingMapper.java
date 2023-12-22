package com.interview.application.domain.mapper;

import com.interview.application.domain.RoomBooking;
import org.mapstruct.Mapper;

@Mapper
public interface ReCreateRoomBookingMapper {

    RoomBooking map(RoomBooking RoomBooking);

}
