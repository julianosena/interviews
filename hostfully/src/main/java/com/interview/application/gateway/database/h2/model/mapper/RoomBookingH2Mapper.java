package com.interview.application.gateway.database.h2.model.mapper;

import com.interview.application.domain.RoomBooking;
import com.interview.application.gateway.database.h2.model.RoomBookingH2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RoomBookingH2Mapper {

    @Mapping(target = "booking.roomBookings", ignore = true)
    RoomBookingH2 map(RoomBooking roomBooking);

    @Mapping(target = "booking.roomBookings", ignore = true)
    @Mapping(target = "room.hotel.rooms", ignore = true)
    RoomBooking map(RoomBookingH2 roomBookingH2);

}
