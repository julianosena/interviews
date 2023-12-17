package com.interview.application.domain.fixture

import com.interview.application.domain.RoomAttribute

class RoomAttributeFixture {

    static create(def parameters = [:]) {
        def defaultValues = [
                id : UUID.randomUUID(),
                description: "Free Wi-fi",
        ]
        def values = defaultValues + parameters

        return RoomAttribute.builder()
            .id(values.id as UUID)
            .description(values.description as String)
            .build()
    }

}
