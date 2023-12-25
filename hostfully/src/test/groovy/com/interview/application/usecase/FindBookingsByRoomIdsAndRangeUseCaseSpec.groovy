package com.interview.application.usecase


import com.interview.application.domain.Room
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.FindBookingByRoomIdsAndRangeGateway
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PENDING

class FindBookingsByRoomIdsAndRangeUseCaseSpec extends Specification {

    def findBookingByRoomIdsAndRangeGateway = Mock(FindBookingByRoomIdsAndRangeGateway)
    def findBookingsByRoomIdsAndRangeUseCase = new FindBookingsByRoomIdsAndRangeUseCase(findBookingByRoomIdsAndRangeGateway)

    def "It should return true, because the chosen room supports the total number of guests within the booking"() {
        given: "Valid and existent hotel"
        def hotel = HotelFixture.create()

        and: "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and: "The booker would like to make a booking"
        def booker = BookerFixture.create(id: null)
        and: "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and: "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and: "than, It submits a booking"
        def booking = BookingFixture.create([
                id              : null,
                booker          : booker,
                checkinDate     : checkinDate,
                checkoutDate    : checkoutDate,
                numberOfAdults  : 2,
                numberOfChildren: 1,
                status          : PENDING,
                roomBookings    : []
        ])
        def roomBooking = RoomBookingFixture.create(
                id: null,
                room: selectedRoom,
                booking: booking
        )
        booking.roomBookings.add(roomBooking)
        def roomIds = rooms.stream().map(Room::getId).toList()

        when: "use case is called"
        def bookings = findBookingsByRoomIdsAndRangeUseCase.execute(roomIds, checkinDate, checkoutDate)

        then : "find bookings by room ids and range process should be called and return non empty bookings list"
        findBookingByRoomIdsAndRangeGateway.execute(roomIds, checkinDate, checkoutDate) >> {
            [BookingFixture.create()]
        }

        and : "bookings list should not be null"
        null != bookings

        and : "bookings should not be empty"
        !bookings.isEmpty()
    }
}
