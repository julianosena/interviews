package com.interview.application.domain.fixture


import com.interview.application.domain.Booker
import com.interview.application.domain.Booking

import java.time.LocalDate
import java.time.LocalDateTime

class BookingFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                booker : BookerFixture.create(),
                checkinDate : LocalDate.now(),
                checkoutDate : LocalDate.now().plusMonths(1),
                numberOfAdults : 2,
                numberOfChildren : 1,
                status : Booking.Status.PENDING,
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return Booking.builder()
                .id(values.id as UUID)
                .previousBooking(values.previousBooking as Booking)
                .booker(values.booker as Booker)
                .checkinDate(values.checkinDate as LocalDate)
                .checkoutDate(values.checkoutDate as LocalDate)
                .numberOfAdults(values.numberOfAdults as Long)
                .numberOfChildren(values.numberOfChildren as Long)
                .roomBookings(values.roomBookings as List)
                .status(values.status as Booking.Status)
                .createdAt(values.createdAt as LocalDateTime)
                .updatedAt(values.updatedAt as LocalDateTime)
                .build()
    }
}

