package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.Range
import com.interview.application.domain.fixture.*
import spock.lang.Specification

import java.time.LocalDate
import java.util.function.Predicate

import static com.interview.application.domain.Booking.Status.PENDING

class IsThereAvailabilityForTheBookingUseCaseSpec extends Specification {

    private FindBookingsByRoomIdsAndRangeUseCase findBookingsByRoomIdsAndRangeUseCase = Mock(FindBookingsByRoomIdsAndRangeUseCase)
    private FindBlocksByRangeUseCase findBlocksByRoomIdsAndRangeUseCase = Mock(FindBlocksByRangeUseCase)
    private FindRoomsByIdsUseCase findRoomsByIdsUseCase = Mock(FindRoomsByIdsUseCase)
    def useCase = new IsThereAvailabilityForTheBookingUseCase(findBookingsByRoomIdsAndRangeUseCase, findBlocksByRoomIdsAndRangeUseCase, findRoomsByIdsUseCase)

    def "It should return true, because, there is availability for the requested booking"(){
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
        boolean isThereAvailability = useCase.execute(booking)

        then : "finding available rooms by room ids process, should return all of rooms within booking"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should not return any block"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute(_ as Range) >> {
            []
        }

        and : "finding bookings by room ids process, should not return any booking within the given period"
        1 * findBookingsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], booking.checkinDate, booking.checkoutDate) >> {
            []
        }

        and : "there is availability to make the given booking"
        isThereAvailability
    }

    def "It should return false, because, the selected room is no longer available"(){
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
        boolean isThereAvailability = useCase.execute(booking)

        then : "finding available rooms by room ids process, should return all of rooms within booking"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            []
        }

        and : "finding blocks by room ids and range process, should not be called"
        0 * findBlocksByRoomIdsAndRangeUseCase.execute(_ as Range)

        and : "finding bookings by room ids process, should not be called"
        0 * findBookingsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], booking.checkinDate, booking.checkoutDate)

        and : "there is no availability to make the given booking"
        !isThereAvailability
    }

    def "It should return false, because, there is one block in the given range"(){
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

        and : "a valid created block register"
        def block = BlockFixture.create()

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(booking)

        then : "finding available rooms by room ids process, should return all of rooms within booking"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return list with one block"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute(_ as Range) >> {
            [block]
        }

        and : "finding bookings by room ids process, should not be called"
        0 * findBookingsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], booking.checkinDate, booking.checkoutDate)

        and : "there is no availability to make the given booking"
        !isThereAvailability
    }

    def "It should return false, because, there is one booking in the given range that is different of the given one"(){
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
        boolean isThereAvailability = useCase.execute(booking)

        then : "finding available rooms by room ids process, should return all of rooms within booking"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return empty list"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute(_ as Range) >> {
            []
        }

        and : "finding bookings by room ids process, should return one different booking within a list"
        1 * findBookingsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], booking.checkinDate, booking.checkoutDate) >> {
            [BookingFixture.create([
                    id : UUID.randomUUID(),
                    booker : booker,
                    checkinDate: checkinDate,
                    checkoutDate: checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomBookings: [roomBooking]
            ])]
        }

        and : "there is no availability to make the given booking"
        !isThereAvailability
    }

    def "It should return true, because, there is only one booking that is the same of the given one, within the given range of dates"(){
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

        and : "than, an existent booking"
        def booking = BookingFixture.create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : PENDING,
                roomBookings: []
        ])
        def roomBooking = RoomBookingFixture.create(
                id : UUID.randomUUID(),
                room : selectedRoom,
                booking : booking
        )
        booking.roomBookings.add(roomBooking)

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(booking)

        then : "finding available rooms by room ids process, should return all of rooms within booking"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return empty list"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute(_ as Range) >> {
            []
        }

        and : "finding bookings by room ids process, should return the same given booking within a list"
        1 * findBookingsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], booking.checkinDate, booking.checkoutDate) >> {
            [booking]
        }

        and : "there is availability to make the given booking"
        isThereAvailability
    }

    def "It should return false, because, there is more than one booking, within the given range of dates"(){
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
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : PENDING,
                roomBookings: []
        ])
        def roomBooking = RoomBookingFixture.create(
                id : UUID.randomUUID(),
                room : selectedRoom,
                booking : booking
        )
        booking.roomBookings.add(roomBooking)

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(booking)

        then : "finding available rooms by room ids process, should return all of rooms within booking"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return empty list"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute(_ as Range) >> {
            []
        }

        and : "finding bookings by room ids process, should return more than one booking within a list for the given range"
        1 * findBookingsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], booking.checkinDate, booking.checkoutDate) >> {
            [BookingFixture.create(), BookingFixture.create()]
        }

        and : "there is no availability to make the given booking"
        !isThereAvailability
    }
}
