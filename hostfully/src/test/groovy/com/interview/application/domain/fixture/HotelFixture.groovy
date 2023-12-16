package com.interview.application.domain.fixture

import com.interview.application.domain.Hotel

import java.time.LocalDateTime

class HotelFixture {

    static def create(Map<String, String> parameters = [
            id : 1L,
            name : "Z Hotel of London",
            createdAt : LocalDateTime.now(),
            updatedAt : LocalDateTime.now()
    ]) {
        return Hotel.builder()
                .id(parameters.id as Long)
                .name(parameters.name as String)
                .createdAt(parameters.createdAt as LocalDateTime)
                .updatedAt(parameters.updatedAt as LocalDateTime)
                .build()
    }
}

