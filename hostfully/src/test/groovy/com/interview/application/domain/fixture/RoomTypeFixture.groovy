package com.interview.application.domain.fixture

import com.interview.application.domain.RoomType

import java.time.Instant

class RoomTypeFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                name: "Second",
                description: "19X",
                rateAdult : new BigDecimal("100.00"),
                rateChildren : new BigDecimal("50.00"),
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return RoomType.builder()
                .id(values.id as UUID)
                .name(values.name as String)
                .description(values.description as String)
                .rateAdult(values.rateAdult as BigDecimal)
                .rateChildren(values.rateChildren as BigDecimal)
                .createdAt(values.createdAt as Instant)
                .updatedAt(values.updatedAt as Instant)
                .build()
    }
}

