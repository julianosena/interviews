package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByIdGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBookingByIdUseCase {

    private final FindBookingByIdGateway findBookingByIdGateway;

    public Optional<Booking> execute(final UUID id){
        return findBookingByIdGateway.execute(id);
    }
}
