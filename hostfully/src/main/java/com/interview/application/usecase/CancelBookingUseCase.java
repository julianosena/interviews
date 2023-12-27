package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.SaveBookingGateway;
import com.interview.application.usecase.exception.NotFoundUseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CancelBookingUseCase {

    private final FindBookingByIdUseCase findBookingByIdUseCase;
    private final SaveBookingGateway saveBookingGateway;

    public Booking execute(final UUID id){
        Assert.notNull(id, "You must inform the id to cancel the booking");

        Booking booking = this.findBookingByIdUseCase.execute(id)
                .orElseThrow(() -> new NotFoundUseCaseException("You can not cancel a non existent booking"));

        booking.cancel();

        return saveBookingGateway.execute(booking);
    }

}
