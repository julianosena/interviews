package com.interview.application.domain.fixture


import com.interview.application.domain.RoomType

import java.time.LocalDateTime

class RoomTypeFixture {

    static def create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                name: "Second",
                description: "19X",
                rateAdult : new BigDecimal("100.00"),
                rateChildren : new BigDecimal("50.00"),
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return RoomType.builder()
                .id(values.id as UUID)
                .name(values.name as String)
                .description(values.description as String)
                .rateAdult(values.rateAdult as BigDecimal)
                .rateChildren(values.rateChildren as BigDecimal)
                .createdAt(values.createdAt as LocalDateTime)
                .updatedAt(values.updatedAt as LocalDateTime)
                .build()
    }
}

