package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.request.CreateBookingRequest;
import com.interview.application.domain.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CreateRoomBookingRequestMapper {

    @Mapping(source = "roomId", target = "room.id")
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RoomBooking map(CreateBookingRequest.RoomBooking roomBooking);

}
