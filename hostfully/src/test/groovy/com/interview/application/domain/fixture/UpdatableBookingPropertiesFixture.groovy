package com.interview.application.domain.fixture


import com.interview.application.domain.UpdatableBookingProperties

import java.time.LocalDate

class UpdatableBookingPropertiesFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                checkinDate : LocalDate.now(),
                checkoutDate : LocalDate.now().plusMonths(1),
        ]
        def values = defaultValues + parameters

        return UpdatableBookingProperties.builder()
                .checkinDate(values.checkinDate as LocalDate)
                .checkoutDate(values.checkoutDate as LocalDate)
                .roomBookings(values.roomBookings as List)
                .build()
    }
}

