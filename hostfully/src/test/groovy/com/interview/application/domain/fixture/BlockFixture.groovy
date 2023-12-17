package com.interview.application.domain.fixture

import com.interview.application.domain.Block

import java.time.LocalDate
import java.time.LocalDateTime

class BlockFixture {

    static Block create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                start : LocalDate.now(),
                end : LocalDate.now().plusMonths(1),
                createdAt : LocalDateTime.now(),
                updatedAt : LocalDateTime.now()
        ]
        def values = defaultValues + parameters

        return Block.builder()
            .id(values.id as UUID)
            .start(values.start as LocalDate)
            .end(values.end as LocalDate)
            .build()
    }
}