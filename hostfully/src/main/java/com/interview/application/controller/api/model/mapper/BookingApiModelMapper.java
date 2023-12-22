package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.BookingApiModel;
import com.interview.application.domain.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingApiModelMapper {

    BookingApiModel map(Booking booking);

}
