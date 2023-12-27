package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.request.CreateBookingRequest

class CreateBookingRequestRoomBookingFixture {

    static def create(def parameters = [:]){
        def defaultValues = [
                roomId : UUID.randomUUID(),
                guestName : "Fulano de Tal",
                guestEmail : "fulano.tal@gmail.com"
        ]
        def values = defaultValues + parameters

        return CreateBookingRequest.RoomBooking.builder()
            .roomId(values.roomId as String)
            .guestName(values.guestName as String)
            .guestEmail(values.guestEmail as String)
            .build()
    }
}
