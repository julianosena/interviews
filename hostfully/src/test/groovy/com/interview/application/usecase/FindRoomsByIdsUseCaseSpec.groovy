package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.Room
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.FindRoomsByIdsGateway
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PENDING

class FindRoomsByIdsUseCaseSpec extends Specification {

    def findRoomsByIdsGateway = Mock(FindRoomsByIdsGateway)
    def findRoomsByIdsUseCase = new FindRoomsByIdsUseCase(findRoomsByIdsGateway)

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
        def roomIds = rooms.stream().map(Room::getId).toList();

        when : "use case is called"
        def foundRooms = findRoomsByIdsUseCase.execute(roomIds)

        then : "find given rooms within the booking process, should be called and return the room(s)"
        findRoomsByIdsGateway.execute(_ as List) >> {
            RoomFixture.list(hotel)
        }

        and: "the result should not be null"
        null != foundRooms

        and : "not empty"
        !foundRooms.isEmpty()
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
        def roomIds = rooms.stream().map(Room::getId).toList();

        when : "use case is called"
        List<Room> foundRooms = findRoomsByIdsUseCase.execute(roomIds, Room::isAvailable)

        then : "find given rooms within the booking process, should be called and return the room(s)"
        findRoomsByIdsGateway.execute(_ as List) >> {
            RoomFixture.list(hotel)
        }

        and: "the result should not be null"
        null != foundRooms

        and : "not empty"
        !foundRooms.isEmpty()

        and : "the rooms should be available"
        foundRooms*.available.every { it }
    }
}