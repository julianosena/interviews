package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Reservation;
import com.interview.application.gateway.CreateReservationGateway;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationH2Gateway implements CreateReservationGateway {

    @Override
    public Reservation execute(Reservation reservation) {
        return null;
    }

}
