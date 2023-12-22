package com.interview.application.domain.fixture

import com.interview.application.domain.Hotel

import java.time.Instant

class HotelFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                name : "Z Hotel of London",
                rooms : [],
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]

        def values = defaultValues + parameters

        return Hotel.builder()
                .id(values.id as UUID)
                .name(values.name as String)
                .rooms(values.rooms as List)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }
}

