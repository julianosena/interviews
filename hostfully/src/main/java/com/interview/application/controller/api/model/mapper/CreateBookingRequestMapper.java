package com.interview.application.controller.api.model.mapper;

import com.interview.application.controller.api.model.request.CreateBookingRequest;
import com.interview.application.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CreateBookingRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roomBookings", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "previousBooking", ignore = true)
    Booking map(CreateBookingRequest request);

}
