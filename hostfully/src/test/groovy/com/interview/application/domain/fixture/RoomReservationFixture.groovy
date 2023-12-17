package com.interview.application.domain.fixture

import com.interview.application.domain.Room
import com.interview.application.domain.RoomReservation

import java.time.LocalDateTime

class RoomReservationFixture {

    static def create(def parameters = [:]){
        def defaultValues = [
                id : UUID.randomUUID(),
                guestName : "Fulano de Tal",
                guestEmail : "fulano.tal@gmail.com",
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return RoomReservation.builder()
            .id(values.id as UUID)
            .room(values.room as Room)
            .guestName(values.name as String)
            .guestEmail(values.email as String)
            .createdAt(values.createdAt as LocalDateTime)
            .updatedAt(values.updatedAt as LocalDateTime)
            .build()
    }
}
