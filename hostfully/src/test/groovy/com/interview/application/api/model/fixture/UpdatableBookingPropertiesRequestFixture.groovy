package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.request.UpdatableBookingPropertiesRequest

import java.time.LocalDate

class UpdatableBookingPropertiesRequestFixture {

    static UpdatableBookingPropertiesRequest create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                checkinDate : LocalDate.now().plusDays(1),
                checkoutDate : LocalDate.now().plusMonths(1),
                roomBookings : [UpdatableRoomBookingPropertiesRequestFixture.create()]
        ]
        def values = defaultValues + parameters

        return UpdatableBookingPropertiesRequest.builder()
            .checkinDate(values.checkinDate as LocalDate)
            .checkoutDate(values.checkoutDate as LocalDate)
            .roomBookings(values.roomBookings as List)
            .build()
    }
}