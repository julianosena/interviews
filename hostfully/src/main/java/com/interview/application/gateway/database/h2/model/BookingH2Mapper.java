package com.interview.application.gateway.database.h2.model;

import com.interview.application.domain.Booking;
import org.mapstruct.Mapper;

@Mapper
public interface BookingH2Mapper {

    Booking map(BookingH2 booking);

    BookingH2 map(Booking booking);

}
