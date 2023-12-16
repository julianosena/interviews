package com.interview.application.usecase;

import com.interview.application.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationUseCase {

    public Reservation execute(Reservation reservation){
        return reservation;
    }

}
