package com.interview.application.gateway;

import com.interview.application.domain.Booking;

import java.util.Optional;
import java.util.UUID;

public interface FindBookingByPreviousBookingGateway {

    Optional<Booking> execute(UUID id);

}
