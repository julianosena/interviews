package com.interview.application.gateway;

import com.interview.application.domain.Booking;

public interface CreateBookingGateway {

    Booking execute(Booking booking);

}
