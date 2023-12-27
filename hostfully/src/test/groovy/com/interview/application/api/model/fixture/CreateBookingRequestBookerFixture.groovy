package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.request.CreateBookingRequest

class CreateBookingRequestBookerFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                firstName : "Fulano",
                lastName : "Del Toro",
                email : "fulano.toro@gmail.com",
                address : CreateBookingRequestBookerAddressFixture.create()
        ]
        def values = defaultValues + parameters

        return CreateBookingRequest.Booker.builder()
                .firstName(values.firstName as String)
                .lastName(values.lastName as String)
                .email(values.email as String)
                .address(values.address as CreateBookingRequest.Booker.Address)
                .build()
    }
}

