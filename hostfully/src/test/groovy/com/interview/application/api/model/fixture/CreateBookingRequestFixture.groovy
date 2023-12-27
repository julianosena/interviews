package com.interview.application.api.model.fixture


import com.interview.application.controller.api.model.request.CreateBookingRequest

import java.time.LocalDate

class CreateBookingRequestFixture {

    static CreateBookingRequest create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                booker : CreateBookingRequestBookerFixture.create(),
                checkinDate : LocalDate.now().plusDays(1),
                checkoutDate : LocalDate.now().plusMonths(1),
                numberOfAdults : 2,
                numberOfChildren : 1,
                roomBookings : [CreateBookingRequestRoomBookingFixture.create()]
        ]
        def values = defaultValues + parameters

        return CreateBookingRequest.builder()
                .booker(values.booker as CreateBookingRequest.Booker)
                .checkinDate(values.checkinDate as LocalDate)
                .checkoutDate(values.checkoutDate as LocalDate)
                .numberOfAdults(values.numberOfAdults as Long)
                .numberOfChildren(values.numberOfChildren as Long)
                .roomBookings(values.roomBookings as List)
                .build()
    }
}

