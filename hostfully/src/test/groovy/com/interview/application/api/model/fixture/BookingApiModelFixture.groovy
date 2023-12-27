package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.BookerApiModel
import com.interview.application.controller.api.model.BookingApiModel

import java.time.Instant
import java.time.LocalDate

class BookingApiModelFixture {

    static BookingApiModel create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                booker : BookerApiModelFixture.create(),
                checkinDate : LocalDate.now().plusDays(1),
                checkoutDate : LocalDate.now().plusMonths(1),
                numberOfAdults : 2,
                numberOfChildren : 1,
                roomBookings : [RoomBookingApiModelFixture.create()],
                status : BookingApiModel.Status.PENDING,
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return BookingApiModel.builder()
                .id(values.id as UUID)
                .previousBooking(values.previousBooking as BookingApiModel)
                .booker(values.booker as BookerApiModel)
                .checkinDate(values.checkinDate as LocalDate)
                .checkoutDate(values.checkoutDate as LocalDate)
                .numberOfAdults(values.numberOfAdults as Long)
                .numberOfChildren(values.numberOfChildren as Long)
                .roomBookings(values.roomBookings as List)
                .status(values.status as BookingApiModel.Status)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }
}

