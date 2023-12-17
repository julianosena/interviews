package com.interview.application.domain.fixture


import com.interview.application.domain.Booker
import com.interview.application.domain.Reservation

import java.time.LocalDate
import java.time.LocalDateTime

class ReservationFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                booker : BookerFixture.create(),
                checkinDate : LocalDate.now(),
                checkoutDate : LocalDate.now().plusMonths(1),
                numberOfAdults : 2,
                numberOfChildren : 1,
                status : Reservation.Status.PENDING,
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return Reservation.builder()
                .id(values.id as UUID)
                .booker(values.booker as Booker)
                .checkinDate(values.checkinDate as LocalDate)
                .checkoutDate(values.checkoutDate as LocalDate)
                .numberOfAdults(values.numberOfAdults as Long)
                .numberOfChildren(values.numberOfChildren as Long)
                .roomReservations(values.roomReservations as List)
                .status(values.status as Reservation.Status)
                .createdAt(values.createdAt as LocalDateTime)
                .updatedAt(values.updatedAt as LocalDateTime)
                .build()
    }
}

