package com.interview.application.domain.fixture

import com.interview.application.domain.Address
import com.interview.application.domain.Booker

import java.time.LocalDateTime

class BookerFixture {

    static def create(def parameters = [
            id : 1L,
            firstName : "Fulano",
            lastName : "Del Toro",
            email : "fulano.toro@gmail.com",
            createdAt : LocalDateTime.now(),
            updatedAt : LocalDateTime.now()
    ]) {
        return Booker.builder()
                .id(parameters.id as Long)
                .firstName(parameters.firstName as String)
                .lastName(parameters.lastName as String)
                .email(parameters.email as String)
                .address(parameters.address as Address)
                .createdAt(parameters.createdAt as LocalDateTime)
                .updatedAt(parameters.updatedAt as LocalDateTime)
                .build()
    }
}

