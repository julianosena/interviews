package com.interview.application.usecase;

import com.interview.application.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsThereAvailabilityForTheReservationUseCase {

    public boolean execute(final Reservation reservation) {
        return true;
    }

}
