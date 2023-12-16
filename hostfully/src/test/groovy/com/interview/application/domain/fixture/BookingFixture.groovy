package com.interview.application.domain.fixture


import com.interview.application.domain.Booker
import com.interview.application.domain.Reservation

import java.time.LocalDateTime

class BookingFixture {

    static def create(def parameters = [
            id : 1L,
            status : Reservation.Status.WAITING_PAYMENT,
            createdAt : LocalDateTime.now(),
            updatedAt : LocalDateTime.now()
    ]) {
        return Reservation.builder()
                .id(parameters.id as Long)
                .booker(parameters.booker as Booker)
                .status(parameters.status as Reservation.Status)
                .createdAt(parameters.createdAt as LocalDateTime)
                .updatedAt(parameters.updatedAt as LocalDateTime)
                .build()
    }
}

