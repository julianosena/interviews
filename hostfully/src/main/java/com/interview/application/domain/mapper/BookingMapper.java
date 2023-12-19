package com.interview.application.domain.mapper;

import com.interview.application.domain.Booking;
import com.interview.application.domain.UpdatableBookingProperties;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface BookingMapper {

    @Mapping(target = "roomBookings", ignore = true)
    @Mapping(target = "checkinDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "checkoutDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(UpdatableBookingProperties source, @MappingTarget Booking booking);

}