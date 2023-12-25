package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByPreviousBookingIdGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoesTheBookingHaveParentBookingUseCase {

    private final FindBookingByPreviousBookingIdGateway gateway;

    public boolean execute(final Booking booking){
        return gateway.execute(booking.getId()).isPresent();
    }

}
