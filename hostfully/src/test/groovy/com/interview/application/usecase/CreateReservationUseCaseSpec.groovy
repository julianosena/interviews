package com.interview.application.usecase


import com.interview.application.domain.fixture.AddressFixture
import com.interview.application.domain.fixture.BookerFixture
import com.interview.application.domain.fixture.BookingFixture
import com.interview.application.domain.fixture.BookingReservationFixture
import com.interview.application.domain.fixture.HotelFixture
import com.interview.application.domain.fixture.RoomFixture
import spock.lang.Specification

class CreateReservationUseCaseSpec extends Specification {

    def useCase = new CreateReservationUseCase()

    def "It should create booking with success"(){
        given: "A valid non created reservation"
        def address = AddressFixture.create()
        def booker = BookerFixture.create(address : address)
        def hotel = HotelFixture.create()
        def room = RoomFixture.create(hotel : hotel)
        def reservations = [BookingReservationFixture.create(id : null, room : room)]
        def booking = BookingFixture.create(booker : booker)

        when: "use case is called"
        def result = useCase.execute(booking)

        then: "created reservation should not be null"
        result != null
    }
}
