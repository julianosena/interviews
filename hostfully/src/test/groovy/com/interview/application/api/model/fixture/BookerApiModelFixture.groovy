package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.AddressApiModel
import com.interview.application.controller.api.model.BookerApiModel

import java.time.Instant

class BookerApiModelFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                firstName : "Fulano",
                lastName : "Del Toro",
                email : "fulano.toro@gmail.com",
                address : AddressApiModelFixture.create(),
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return BookerApiModel.builder()
                .id(values.id as UUID)
                .firstName(values.firstName as String)
                .lastName(values.lastName as String)
                .email(values.email as String)
                .address(values.address as AddressApiModel)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }
}

