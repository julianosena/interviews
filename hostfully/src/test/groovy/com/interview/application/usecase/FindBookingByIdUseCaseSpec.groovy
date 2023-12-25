package com.interview.application.usecase


import com.interview.application.domain.fixture.BookingFixture
import com.interview.application.gateway.FindBookingByIdGateway
import spock.lang.Specification

class FindBookingByIdUseCaseSpec extends Specification {

    def findBookingByIdGateway = Mock(FindBookingByIdGateway)
    def findBookingByIdUseCase = new FindBookingByIdUseCase(findBookingByIdGateway)

    def "It should find a booking by its id with success"(){
        given: "Valid and existent booking"
        def booking = BookingFixture.create()

        when: "use case is called"
        def optional = findBookingByIdUseCase.execute(booking.id)

        then: "find booking process should be called once and return a valid existent booking"
        1 * findBookingByIdGateway.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "optional as response should not be null or empty"
        null != optional
        !optional.isEmpty()
    }
}
