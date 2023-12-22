package com.interview.application.domain.mapper;

import com.interview.application.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReCreateBookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "booker.id", ignore = true)
    @Mapping(target = "booker.address.id", ignore = true)
    @Mapping(target = "roomBookings", ignore = true)
    @Mapping(target = "previousBooking", ignore = true)
    Booking map(Booking source);

}