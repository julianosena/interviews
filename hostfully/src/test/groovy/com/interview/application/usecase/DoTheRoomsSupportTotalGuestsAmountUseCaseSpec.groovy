package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.fixture.*
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

import java.time.LocalDate
import java.util.function.Predicate

import static com.interview.application.domain.Booking.Status.PENDING

class DoTheRoomsSupportTotalGuestsAmountUseCaseSpec extends Specification {

    def findRoomsByIdsUseCase = Mock(FindRoomsByIdsUseCase)
    def doTheRoomsSupportTotalGuestsAmountUseCase = new DoTheRoomsSupportTotalGuestsAmountUseCase(findRoomsByIdsUseCase)

    def "It should return true, because the chosen room supports the total number of guests within the booking"(){
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
        boolean result = doTheRoomsSupportTotalGuestsAmountUseCase.execute(booking)

        then : "find given rooms within the booking process, should be called and return the room(s)"
        findRoomsByIdsUseCase.execute(_ as List, _ as Predicate) >> {
            rooms
        }

        and: "the result should be true, because the booking room supports the total number of guests"
        result
    }

    def "It should throw an exception because there are not rooms for the given ones"(){
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
        doTheRoomsSupportTotalGuestsAmountUseCase.execute(booking)

        then : "find given rooms within the booking process, should be called and return the room(s)"
        findRoomsByIdsUseCase.execute(_ as List, _ as Predicate) >> {
            []
        }

        and: "the exception shoul be the expected"
        def e = thrown(UseCaseException)

        and : "its message as well"
        e.message == "The selected rooms don't exist, or they are not available, check your solicitation and try again"
    }
}