package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.domain.Room;
import com.interview.application.domain.RoomBooking;
import org.springframework.stereotype.Component;

@Component
public class DoesTheBookingHavaDuplicatedRoomsUseCase {

    public boolean execute(final Booking booking){
        long count = booking.getRoomBookings()
                .stream()
                .map(RoomBooking::getRoom)
                .map(Room::getId).count();
        long distinct = booking.getRoomBookings()
                .stream()
                .map(RoomBooking::getRoom)
                .map(Room::getId).distinct().count();

        return distinct != count;
    }

}
