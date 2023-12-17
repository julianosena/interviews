package com.interview.application.domain.fixture

import com.interview.application.domain.Address

import java.time.LocalDateTime

class AddressFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                street : "R. Sebatian 10",
                city : "Campinas",
                postalCode : "4133-234",
                country : "Portugal",
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return Address.builder()
                .id(values.id as UUID)
                .street(values.street as String)
                .city(values.city as String)
                .postalCode(values.postalCode as String)
                .country(values.country as String)
                .createdAt(values.createdAt as LocalDateTime)
                .updatedAt(values.updatedAt as LocalDateTime)
                .build()
    }
}

