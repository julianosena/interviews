package com.interview.application.api.model.fixture

import com.interview.application.controller.api.model.request.CreateBlockApiRequest

import java.time.LocalDate

class CreateBlockApiRequestFixture {

    static CreateBlockApiRequest create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                start : LocalDate.now(),
                end : LocalDate.now().plusMonths(1)
        ]
        def values = defaultValues + parameters

        return CreateBlockApiRequest.builder()
            .start(values.start as LocalDate)
            .end(values.end as LocalDate)
            .build()
    }
}