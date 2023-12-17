package com.interview.application.gateway;

import com.interview.application.domain.Reservation;

public interface CreateReservationGateway {

    Reservation execute(Reservation reservation);

}
