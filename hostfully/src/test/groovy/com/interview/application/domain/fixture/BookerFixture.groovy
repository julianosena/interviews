package com.interview.application.domain.fixture

import com.interview.application.domain.Address
import com.interview.application.domain.Booker

import java.time.Instant

class BookerFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                firstName : "Fulano",
                lastName : "Del Toro",
                email : "fulano.toro@gmail.com",
                address : AddressFixture.create(),
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return Booker.builder()
                .id(values.id as UUID)
                .firstName(values.firstName as String)
                .lastName(values.lastName as String)
                .email(values.email as String)
                .address(values.address as Address)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }
}

