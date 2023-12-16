package com.interview.application.domain.fixture

import com.interview.application.domain.Hotel
import com.interview.application.domain.Room

import java.time.LocalDateTime

class RoomFixture {

    static def create(def parameters = [
            id : 1L,
            name : "Luxurious single bedroom",
            type : Room.Type.SINGLE,
            attributes : [ "wifi" : "100mbs", "microwave" : "1000 watts"],
            maxOccupancy : 3,
            rateAdult : new BigDecimal(112.10),
            rateChildren : new BigDecimal(66.5),
            createdAt : LocalDateTime.now(),
            updatedAt : LocalDateTime.now()
    ]) {
        return Room.builder()
                .id(parameters.id as Long)
                .name(parameters.name as String)
                .type(Room.Type.SINGLE)
                .attributes(parameters.attributes as Map)
                .hotel(parameters.hotel as Hotel)
                .maxOccupancy(parameters.maxOccupancy as Long)
                .rateAdult(parameters.rateAdult as BigDecimal)
                .rateChildren(parameters.rateChildren as BigDecimal)
                .createdAt(parameters.createdAt as LocalDateTime)
                .updatedAt(parameters.updatedAt as LocalDateTime)
                .build()
    }
}

