package com.interview.application.gateway.database.h2.model.translator;

import com.interview.application.domain.Booking;
import com.interview.application.domain.RoomBooking;
import com.interview.application.gateway.database.h2.model.BookingH2;
import com.interview.application.gateway.database.h2.model.mapper.BookingH2Mapper;
import com.interview.application.gateway.database.h2.model.mapper.RoomBookingH2Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class BookingH2ToBookingTranslator {

    private final static BookingH2Mapper bookingH2Mapper = Mappers.getMapper(BookingH2Mapper.class);
    private final static RoomBookingH2Mapper roomBookingH2Mapper = Mappers.getMapper(RoomBookingH2Mapper.class);

    public static Booking translate(BookingH2 h2){
        Booking booking = bookingH2Mapper.map(h2);
        List<RoomBooking> roomsBookings = h2.getRoomBookings().stream().map(roomBookingH2Mapper::map).toList();
        booking.setRoomBookings(roomsBookings);
        return booking;
    }

}
