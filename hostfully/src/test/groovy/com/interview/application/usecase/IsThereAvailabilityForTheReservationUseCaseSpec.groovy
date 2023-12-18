package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.Range
import com.interview.application.domain.fixture.*
import spock.lang.Specification

import java.time.LocalDate
import java.util.function.Predicate

class IsThereAvailabilityForTheReservationUseCaseSpec extends Specification {

    private FindReservationsByRoomIdsAndRangeUseCase findReservationsByRoomIdsAndRangeUseCase = Mock(FindReservationsByRoomIdsAndRangeUseCase)
    private FindBlocksByRoomIdsAndRangeUseCase findBlocksByRoomIdsAndRangeUseCase = Mock(FindBlocksByRoomIdsAndRangeUseCase)
    private FindRoomsByIdsUseCase findRoomsByIdsUseCase = Mock(FindRoomsByIdsUseCase)
    def useCase = new IsThereAvailabilityForTheReservationUseCase(findReservationsByRoomIdsAndRangeUseCase, findBlocksByRoomIdsAndRangeUseCase, findRoomsByIdsUseCase)

    def "It should return true, because, there is availability for the requested reservation"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a reservation"
        def roomReservation = RoomReservationFixture.create(id : null, room : selectedRoom)
        def reservation = ReservationFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomReservations: [roomReservation]
        ])

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(reservation)

        then : "finding available rooms by room ids process, should return all of rooms within reservation"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should not return any block"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute([selectedRoom.id], _ as Range) >> {
            []
        }

        and : "finding reservations by room ids process, should not return any reservation within the given period"
        1 * findReservationsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], reservation.checkinDate, reservation.checkoutDate) >> {
            []
        }

        and : "there is availability to make the given reservation"
        isThereAvailability
    }

    def "It should return false, because, the selected room is no longer available"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a reservation"
        def roomReservation = RoomReservationFixture.create(id : null, room : selectedRoom)
        def reservation = ReservationFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomReservations: [roomReservation]
        ])

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(reservation)

        then : "finding available rooms by room ids process, should return all of rooms within reservation"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            []
        }

        and : "finding blocks by room ids and range process, should not be called"
        0 * findBlocksByRoomIdsAndRangeUseCase.execute([selectedRoom.id], _ as Range)

        and : "finding reservations by room ids process, should not be called"
        0 * findReservationsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], reservation.checkinDate, reservation.checkoutDate)

        and : "there is no availability to make the given reservation"
        !isThereAvailability
    }

    def "It should return false, because, there is one block in the given range"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a reservation"
        def roomReservation = RoomReservationFixture.create(id : null, room : selectedRoom)
        def reservation = ReservationFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomReservations: [roomReservation]
        ])

        and : "a valid created block register"
        def block = BlockFixture.create()

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(reservation)

        then : "finding available rooms by room ids process, should return all of rooms within reservation"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return list with one block"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute([selectedRoom.id], _ as Range) >> {
            [block]
        }

        and : "finding reservations by room ids process, should not be called"
        0 * findReservationsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], reservation.checkinDate, reservation.checkoutDate)

        and : "there is no availability to make the given reservation"
        !isThereAvailability
    }

    def "It should return false, because, there is one reservation in the given range that is different of the given one"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a reservation"
        def roomReservation = RoomReservationFixture.create(id : null, room : selectedRoom)
        def reservation = ReservationFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomReservations: [roomReservation]
        ])

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(reservation)

        then : "finding available rooms by room ids process, should return all of rooms within reservation"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return empty list"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute([selectedRoom.id], _ as Range) >> {
            []
        }

        and : "finding reservations by room ids process, should return one different reservation within a list"
        1 * findReservationsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], reservation.checkinDate, reservation.checkoutDate) >> {
            [ReservationFixture.create([
                    id : UUID.randomUUID(),
                    booker : booker,
                    checkinDate: checkinDate,
                    checkoutDate: checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomReservations: [roomReservation]
            ])]
        }

        and : "there is no availability to make the given reservation"
        !isThereAvailability
    }

    def "It should return true, because, there is only one reservation that is the same of the given one, within the given range of dates"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a reservation"
        def roomReservation = RoomReservationFixture.create(id : null, room : selectedRoom)
        def reservation = ReservationFixture.create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomReservations: [roomReservation]
        ])

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(reservation)

        then : "finding available rooms by room ids process, should return all of rooms within reservation"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return empty list"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute([selectedRoom.id], _ as Range) >> {
            []
        }

        and : "finding reservations by room ids process, should return the same given reservation within a list"
        1 * findReservationsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], reservation.checkinDate, reservation.checkoutDate) >> {
            [reservation]
        }

        and : "there is availability to make the given reservation"
        isThereAvailability
    }

    def "It should return false, because, there is more than one reservation, within the given range of dates"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, It submits a reservation"
        def roomReservation = RoomReservationFixture.create(id : null, room : selectedRoom)
        def reservation = ReservationFixture.create([
                id : null,
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomReservations: [roomReservation]
        ])

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(reservation)

        then : "finding available rooms by room ids process, should return all of rooms within reservation"
        1 * findRoomsByIdsUseCase.execute([selectedRoom.id], _ as Predicate) >> {
            [selectedRoom]
        }

        and : "finding blocks by room ids and range process, should return empty list"
        1 * findBlocksByRoomIdsAndRangeUseCase.execute([selectedRoom.id], _ as Range) >> {
            []
        }

        and : "finding reservations by room ids process, should return more than one reservation within a list for the given range"
        1 * findReservationsByRoomIdsAndRangeUseCase.execute([selectedRoom.id], reservation.checkinDate, reservation.checkoutDate) >> {
            [ReservationFixture.create(), ReservationFixture.create()]
        }

        and : "there is no availability to make the given reservation"
        !isThereAvailability
    }
}
