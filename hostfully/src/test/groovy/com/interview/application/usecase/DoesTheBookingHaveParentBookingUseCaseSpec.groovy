package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.FindBookingByPreviousBookingIdGateway
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PENDING

class DoesTheBookingHaveParentBookingUseCaseSpec extends Specification {

    def findBookingByPreviousBookingIdGateway = Mock(FindBookingByPreviousBookingIdGateway)
    def doesTheBookingHaveParentBookingUseCase = new DoesTheBookingHaveParentBookingUseCase(findBookingByPreviousBookingIdGateway)

    def "It should return true, because the booking was canceled and re-booked with success"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a booking"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a booking"
        def booking = BookingFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : PENDING,
                roomBookings: []
        ])
        def roomBooking = RoomBookingFixture.create(
                id : null,
                room : selectedRoom,
                booking : booking
        )
        booking.roomBookings.add(roomBooking)

        when : "use case is called"
        boolean result = doesTheBookingHaveParentBookingUseCase.execute(booking)

        then : "find booking by previous booking process should return a canceled booking"
        findBookingByPreviousBookingIdGateway.execute(booking.id) >> {
            Optional.of(BookingFixture.create())
        }

        and: "the result should be true, because the booking was canceled and re-booked"
        result
    }

    def "It should return false, because the booking was not canceled and re-booked"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a booking"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a booking"
        def booking = BookingFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : PENDING,
                roomBookings: []
        ])
        def roomBooking = RoomBookingFixture.create(
                id : null,
                room : selectedRoom,
                booking : booking
        )
        booking.roomBookings.add(roomBooking)

        when : "use case is called"
        boolean result = doesTheBookingHaveParentBookingUseCase.execute(booking)

        then : "find booking by previous booking process should return nothing"
        findBookingByPreviousBookingIdGateway.execute(booking.id) >> {
            Optional.empty()
        }

        and: "the result should be false, because the booking was not canceled and re-booked"
        !result
    }
}