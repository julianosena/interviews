package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByPreviousBookingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoesTheBookingHasParentBookingUseCase {

    private final FindBookingByPreviousBookingGateway gateway;

    public boolean execute(final Booking booking){
        return gateway.execute(booking.getId()).isPresent();
    }

}
