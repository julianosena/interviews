package com.interview.application.domain.fixture


import com.interview.application.domain.UpdatableRoomBookingProperties

class UpdatableRoomBookingPropertiesFixture {

    static def create(def parameters = [:]){
        def defaultValues = [
                roomId : UUID.randomUUID(),
                bookingId : UUID.randomUUID(),
                guestName : "Fulano de Tal",
                guestEmail : "fulano.tal@gmail.com"
        ]
        def values = defaultValues + parameters

        return UpdatableRoomBookingProperties.builder()
            .roomId(values.roomId as UUID)
            .bookingId(values.bookingId as UUID)
            .guestName(values.guestName as String)
            .guestEmail(values.guestEmail as String)
            .build()
    }
}
