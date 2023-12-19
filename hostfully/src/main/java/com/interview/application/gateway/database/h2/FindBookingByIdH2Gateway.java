package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByIdGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindBookingByIdH2Gateway implements FindBookingByIdGateway {

    @Override
    public Optional<Booking> execute(UUID id) {
        return Optional.empty();
    }

}
