package com.interview.application.gateway;

import com.interview.application.domain.Booking;

public interface DeleteBookingGateway {

    void execute(Booking booking);

}
