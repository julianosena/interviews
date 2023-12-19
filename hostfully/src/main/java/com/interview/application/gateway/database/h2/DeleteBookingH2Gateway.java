package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.DeleteBookingGateway;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookingH2Gateway implements DeleteBookingGateway {

    @Override
    public void execute(Booking booking) {
    }

}
