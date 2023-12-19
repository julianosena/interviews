package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.SaveBookingGateway;
import org.springframework.stereotype.Component;

@Component
public class SaveBookingH2Gateway implements SaveBookingGateway {

    @Override
    public Booking execute(Booking booking) {
        return null;
    }

}
