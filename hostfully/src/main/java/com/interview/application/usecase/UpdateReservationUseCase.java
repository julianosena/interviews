package com.interview.application.usecase;

import com.interview.application.domain.Reservation;
import com.interview.application.gateway.CreateReservationGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class UpdateReservationUseCase {

    private final IsThereAvailabilityForTheReservationUseCase isThereAvailabilityForTheReservationUseCase;
    private final CreateReservationGateway createReservationGateway;

    public Reservation execute(final Reservation reservation){
        Assert.notNull(reservation.getId(), "To create reservation, you must inform the id");

        if(isThereAvailabilityForTheReservationUseCase.execute(reservation)){
            return createReservationGateway.execute(reservation);
        }

        throw new UseCaseException("There is no availability for this period and " +
                "room(s) " + reservation.getRoomReservations() + " " +
                reservation.getCheckinDate() + " up to " + reservation.getCheckoutDate());
    }

}
