package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.SaveBookingGateway;
import com.interview.application.gateway.database.h2.model.BookingH2;
import com.interview.application.gateway.database.h2.model.RoomBookingId;
import com.interview.application.gateway.database.h2.model.mapper.BookingH2Mapper;
import com.interview.application.gateway.database.h2.model.translator.BookingH2ToBookingTranslator;
import com.interview.application.gateway.database.h2.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveBookingH2Gateway implements SaveBookingGateway {

    private final BookingRepository bookingRepository;
    private final BookingH2Mapper bookingH2Mapper;

    @Override
    @Transactional
    public Booking execute(final Booking booking) {
        final BookingH2 mapped = bookingH2Mapper.map(booking);
        mapped.getRoomBookings().forEach((roomBooking -> {

            RoomBookingId id = new RoomBookingId();
            id.setBookingId(roomBooking.getBooking().getId());
            id.setRoomId(roomBooking.getRoom().getId());

            roomBooking.setId(id);
        }));
        final BookingH2 persisted = bookingRepository.saveAndFlush(mapped);
        return BookingH2ToBookingTranslator.translate(persisted);
    }

}
