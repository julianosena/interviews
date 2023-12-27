package com.interview.application.api.model.fixture


import com.interview.application.controller.api.model.request.UpdateBlockApiRequest

import java.time.LocalDate

class UpdateBlockApiRequestFixture {

    static UpdateBlockApiRequest create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                start : LocalDate.now(),
                end : LocalDate.now().plusMonths(1)
        ]
        def values = defaultValues + parameters

        return UpdateBlockApiRequest.builder()
            .start(values.start as LocalDate)
            .end(values.end as LocalDate)
            .build()
    }
}