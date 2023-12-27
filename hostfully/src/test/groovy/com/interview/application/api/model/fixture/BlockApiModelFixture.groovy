package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.BlockApiModel

import java.time.Instant
import java.time.LocalDate

class BlockApiModelFixture {

    static BlockApiModel create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                start : LocalDate.now(),
                end : LocalDate.now().plusMonths(1),
                createdAt : Instant.now(),
                updatedAt : Instant.now()
        ]
        def values = defaultValues + parameters

        return BlockApiModel.builder()
            .id(values.id as UUID)
            .start(values.start as LocalDate)
            .end(values.end as LocalDate)
            .createdAt(values.createdAt as Instant)
            .updatedAt(values.updatedAt as Instant)
            .build()
    }
}