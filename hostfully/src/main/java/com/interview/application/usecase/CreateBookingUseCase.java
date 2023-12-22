package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.CreateBookingGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static com.interview.application.domain.Booking.Status.PENDING;

@Component
@RequiredArgsConstructor
public class CreateBookingUseCase {

    private final DoesTheBookingHavaDuplicatedRoomsUseCase doesTheBookingHavaDuplicatedRoomsUseCase;
    private final DoTheRoomsSupportTotalGuestsAmountUseCase doTheRoomsSupportTotalGuestsAmountUseCase;
    private final IsThereAvailabilityForTheBookingUseCase isThereAvailabilityForTheBookingUseCase;
    private final CreateBookingGateway createBookingGateway;

    public Booking execute(final Booking booking){
        Assert.isNull(booking.getId(), "To create a booking, you must not inform the id");

        if(doesTheBookingHavaDuplicatedRoomsUseCase.execute(booking)){
            throw new UseCaseException("It is not allow to book twice for the same room");
        }

        if(doTheRoomsSupportTotalGuestsAmountUseCase.execute(booking)){
            if(isThereAvailabilityForTheBookingUseCase.execute(booking)) {
                booking.setStatus(PENDING);
                booking.setTotalAmount(BigDecimal.ZERO);
                return createBookingGateway.execute(booking);
            }

            throw new UseCaseException("There is no availability for the selected rooms within the given period " +
                    booking.getCheckinDate() + " up to " + booking.getCheckoutDate());
        }

        throw new UseCaseException("The selected rooms dont support the total amount of guests " + booking.getTotalAmountOfGuests());
    }

}
