package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.fixture.*
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PENDING

class DoesTheBookingHaveDuplicatedRoomsUseCaseSpec extends Specification {

    def doesTheBookingHaveDuplicatedRoomsUseCase = new DoesTheBookingHaveDuplicatedRoomsUseCase()

    def "It should return true because the booking has duplicated rooms within"(){
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
        booking.roomBookings.add(roomBooking)

        when : "use case is called"
        boolean result = doesTheBookingHaveDuplicatedRoomsUseCase.execute(booking)

        then : "the result should be true, because there are duplicated rooms within booking"
        result
    }

    def "It should return false because the booking has not duplicated rooms within"(){
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
        def roomBookingOne = RoomBookingFixture.create(
                id : null,
                room : selectedRoom,
                booking : booking
        )
        def roomBookingTwo = RoomBookingFixture.create(
                id : null,
                room : RoomFixture.create(),
                booking : booking
        )
        booking.roomBookings.add(roomBookingOne)
        booking.roomBookings.add(roomBookingTwo)

        when : "use case is called"
        boolean result = doesTheBookingHaveDuplicatedRoomsUseCase.execute(booking)

        then : "the result should be false, because there are not duplicated rooms within booking"
        !result
    }
}
