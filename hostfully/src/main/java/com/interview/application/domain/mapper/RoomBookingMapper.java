package com.interview.application.domain.mapper;

import com.interview.application.domain.RoomBooking;
import com.interview.application.domain.UpdatableRoomBookingProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface RoomBookingMapper {

    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "guestName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "guestEmail", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(UpdatableRoomBookingProperties source, @MappingTarget RoomBooking roomBooking);

}
