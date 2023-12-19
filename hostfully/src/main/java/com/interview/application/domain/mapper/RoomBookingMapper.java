package com.interview.application.domain.mapper;

import com.interview.application.domain.RoomBooking;
import com.interview.application.domain.UpdatableRoomBookingProperties;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface RoomBookingMapper {

    void map(UpdatableRoomBookingProperties source, @MappingTarget RoomBooking roomBooking);

}
