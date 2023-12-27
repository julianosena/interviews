package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.AddressApiModel

import java.time.Instant

class AddressApiModelFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                street : "R. Sebatian 10",
                city : "Campinas",
                postalCode : "4133-234",
                country : "Portugal",
                state : "Porto",
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return AddressApiModel.builder()
                .id(values.id as UUID)
                .street(values.street as String)
                .city(values.city as String)
                .postalCode(values.postalCode as String)
                .country(values.country as String)
                .state(values.state as String)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }
}

