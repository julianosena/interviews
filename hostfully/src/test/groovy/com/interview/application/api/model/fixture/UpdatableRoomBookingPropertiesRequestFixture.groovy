package com.interview.application.api.model.fixture


import com.interview.application.controller.api.model.request.UpdatableRoomBookingPropertiesRequest

class UpdatableRoomBookingPropertiesRequestFixture {

    static UpdatableRoomBookingPropertiesRequest create(def parameters = [:]) {
        def defaultValues = [
                bookingId : UUID.randomUUID(),
                roomId : UUID.randomUUID(),
                guestName : UUID.randomUUID().toString().replace("-", ""),
                guestEmail : UUID.randomUUID().toString().replace("-", "")
        ]
        def values = defaultValues + parameters

        return UpdatableRoomBookingPropertiesRequest.builder()
            .bookingId(values.bookingId as UUID)
            .roomId(values.roomId as UUID)
            .guestName(values.guestName as String)
            .guestEmail(values.guestEmail as String)
            .build()
    }
}