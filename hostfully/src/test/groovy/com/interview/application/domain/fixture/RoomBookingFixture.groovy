package com.interview.application.domain.fixture

import com.interview.application.domain.Booking
import com.interview.application.domain.Room
import com.interview.application.domain.RoomBooking

import java.time.LocalDateTime

class RoomBookingFixture {

    static def create(def parameters = [:]){
        def defaultValues = [
                id : UUID.randomUUID(),
                guestName : "Fulano de Tal",
                guestEmail : "fulano.tal@gmail.com",
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return RoomBooking.builder()
            .id(values.id as UUID)
            .booking(values.booking as Booking)
            .room(values.room as Room)
            .guestName(values.guestName as String)
            .guestEmail(values.guestEmail as String)
            .createdAt(values.createdAt as LocalDateTime)
            .updatedAt(values.updatedAt as LocalDateTime)
            .build()
    }
}
