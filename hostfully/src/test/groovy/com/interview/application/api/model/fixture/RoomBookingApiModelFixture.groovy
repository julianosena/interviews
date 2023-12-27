package com.interview.application.api.model.fixture


import com.interview.application.controller.api.model.RoomApiModel
import com.interview.application.controller.api.model.RoomBookingApiModel

import java.time.Instant

class RoomBookingApiModelFixture {

    static def create(def parameters = [:]){
        def defaultValues = [
                guestName : "Fulano de Tal",
                guestEmail : "fulano.tal@gmail.com",
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return RoomBookingApiModel.builder()
            .room(values.room as RoomApiModel)
            .guestName(values.guestName as String)
            .guestEmail(values.guestEmail as String)
            .createdAt(values.createdAt as Instant)
            .updatedAt(values.updatedAt as Instant)
            .build()
    }
}
