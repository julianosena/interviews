package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.SaveBookingGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class CreateBookingUseCase {

    private final IsThereAvailabilityForTheBookingUseCase isThereAvailabilityForTheBookingUseCase;
    private final SaveBookingGateway saveBookingGateway;

    public Booking execute(final Booking booking){
        Assert.isNull(booking.getId(), "To create a booking, you must not inform the id");

        if(isThereAvailabilityForTheBookingUseCase.execute(booking)){
            return saveBookingGateway.execute(booking);
        }

        throw new UseCaseException("There is no availability for this period and " +
                "room(s) " + booking.getRoomBookings() + " " +
                booking.getCheckinDate() + " up to " + booking.getCheckoutDate());
    }

}
