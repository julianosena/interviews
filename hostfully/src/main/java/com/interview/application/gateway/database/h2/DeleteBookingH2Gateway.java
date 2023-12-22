package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.DeleteBookingGateway;
import com.interview.application.gateway.database.h2.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteBookingH2Gateway implements DeleteBookingGateway {

    private final BookingRepository repository;

    @Override
    public void execute(Booking booking) {
        repository.deleteById(booking.getId());
    }

}
