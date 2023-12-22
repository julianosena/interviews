package com.interview.application.gateway.database.h2.model.mapper;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.database.h2.model.BookingH2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingH2Mapper {

    BookingH2 map(Booking booking);

    @Mapping(target = "roomBookings", ignore = true)
    Booking map(BookingH2 booking);

}
