package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.request.CreateBookingRequest

class CreateBookingRequestBookerAddressFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                street : "R. Sebatian 10",
                city : "Campinas",
                postalCode : "4133-234",
                country : "Portugal",
                state : "Porto"
        ]
        def values = defaultValues + parameters

        return CreateBookingRequest.Booker.Address.builder()
                .street(values.street as String)
                .city(values.city as String)
                .postalCode(values.postalCode as String)
                .country(values.country as String)
                .state(values.state as String)
                .build()
    }
}

