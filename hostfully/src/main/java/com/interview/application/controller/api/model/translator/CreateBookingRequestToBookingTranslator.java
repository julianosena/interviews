package com.interview.application.controller.api.model.translator;

import com.interview.application.controller.api.model.mapper.CreateBookingRequestMapper;
import com.interview.application.controller.api.model.mapper.CreateRoomBookingRequestMapper;
import com.interview.application.controller.api.model.request.CreateBookingRequest;
import com.interview.application.domain.Booking;
import com.interview.application.domain.RoomBooking;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class CreateBookingRequestToBookingTranslator {

    private static final CreateBookingRequestMapper createBookingRequestMapper = Mappers.getMapper(CreateBookingRequestMapper.class);
    private static final CreateRoomBookingRequestMapper createRoomBookingRequestMapper = Mappers.getMapper(CreateRoomBookingRequestMapper.class);

    public static Booking translate(CreateBookingRequest request){
        Booking booking = createBookingRequestMapper.map(request);
        List<RoomBooking> roomBookings = request.getRoomBookings().stream().map(createRoomBookingRequestMapper::map).toList();
        booking.setRoomBookings(roomBookings);
        return booking;
    }

}
