package com.interview.application.usecase

import com.interview.application.domain.RoomFilter
import com.interview.application.domain.fixture.HotelFixture
import com.interview.application.domain.fixture.RoomFixture
import com.interview.application.gateway.FindRoomsByFilterGateway
import spock.lang.Specification

class FindRoomsByFilterUseCaseSpec extends Specification {

    def findRoomsByFilterGateway = Mock(FindRoomsByFilterGateway)
    def findRoomsByFilterUseCase = new FindRoomsByFilterUseCase(findRoomsByFilterGateway)

    def "It should find rooms by filter with success"(){
        given : "Valid hotel"
        def hotel = HotelFixture.create()

        and: "Valid filter"
        def filter = RoomFilter.builder()
            .maxOccupancy(2)
            .available(true)
            .build()

        when: "use case is called"
        def rooms = findRoomsByFilterUseCase.execute(filter)

        then: "find rooms process should be called once and return list of rooms"
        1 * findRoomsByFilterGateway.execute(filter) >> {
            [RoomFixture.list(hotel)]
        }

        and : "list of rooms should not be null"
        null != rooms

        and : "not empty"
        !rooms.isEmpty()
    }
}
