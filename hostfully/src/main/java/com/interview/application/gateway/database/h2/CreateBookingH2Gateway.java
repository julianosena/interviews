package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.CreateBookingGateway;
import com.interview.application.gateway.database.h2.model.BookingH2;
import com.interview.application.gateway.database.h2.model.RoomBookingId;
import com.interview.application.gateway.database.h2.model.RoomH2;
import com.interview.application.gateway.database.h2.model.mapper.BookingH2Mapper;
import com.interview.application.gateway.database.h2.model.translator.BookingH2ToRoomTranslator;
import com.interview.application.gateway.database.h2.repository.BookingRepository;
import com.interview.application.gateway.database.h2.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookingH2Gateway implements CreateBookingGateway {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final BookingH2Mapper bookingH2Mapper;

    @Override
    @Transactional
    public Booking execute(final Booking booking) {
        final BookingH2 mapped = bookingH2Mapper.map(booking);
        mapped.getRoomBookings().forEach((roomBooking -> {
            roomBooking.setId(new RoomBookingId());

            roomBooking.setBooking(mapped);
            RoomH2 room = roomRepository.getReferenceById(roomBooking.getRoom().getId());
            roomBooking.setRoom(room);
        }));
        final BookingH2 persisted = bookingRepository.saveAndFlush(mapped);

        return BookingH2ToRoomTranslator.translate(persisted);
    }

}
