package com.interview.application.domain.fixture

import com.interview.application.domain.Booking
import com.interview.application.domain.Room
import com.interview.application.domain.RoomBooking

import java.time.Instant

class RoomBookingFixture {

    static def create(def parameters = [:]){
        def defaultValues = [
                guestName : "Fulano de Tal",
                guestEmail : "fulano.tal@gmail.com",
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return RoomBooking.builder()
            .room(values.room as Room)
            .booking(values.booking as Booking)
            .guestName(values.guestName as String)
            .guestEmail(values.guestEmail as String)
            .createdAt(values.createdAt as Instant)
            .updatedAt(values.updatedAt as Instant)
            .build()
    }
}
