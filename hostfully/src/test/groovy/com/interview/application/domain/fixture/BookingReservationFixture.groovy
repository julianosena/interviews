package com.interview.application.domain.fixture

import com.interview.application.domain.RoomReservation
import com.interview.application.domain.Room

import java.time.LocalDateTime

class BookingReservationFixture {

    static def create(def parameters = [
        id : 1L,
        guestName : "Fulano de Tal",
        guestEmail : "fulano.tal@gmail.com",
        checkinDate : LocalDateTime.of(2023, 10, 10, 0, 0, 0),
        checkoutDate : LocalDateTime.of(2023, 11, 10, 0, 0, 0),
        numberOfAdults : 2,
        numberOfChildren : 1,
        createdAt : LocalDateTime.now(),
        updatedAt : LocalDateTime.now()
    ]){
        RoomReservation.builder()
            .id(parameters.id as Long)
            .room(parameters.room as Room)
            .guestName(parameters.name as String)
            .guestEmail(parameters.email as String)
            .numberOfAdults(parameters.numberOfAdults as Long)
            .numberOfChildren(parameters.numberOfAdults as Long)
            .createdAt(parameters.createdAt as LocalDateTime)
            .updatedAt(parameters.updatedAt as LocalDateTime)
    }
}
