package com.interview.application.gateway;

import com.interview.application.domain.Booking;

public interface SaveBookingGateway {

    Booking execute(Booking booking);

}
