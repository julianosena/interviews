package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.domain.Hotel
import com.interview.application.domain.UpdatableBookingProperties
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.SaveBookingGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PAID
import static com.interview.application.domain.Booking.Status.PENDING
import static com.interview.application.domain.fixture.BookingFixture.create

class UpdateBookingUseCaseSpec extends Specification {

    def isThereAvailabilityForTheBookingUseCase = Mock(IsThereAvailabilityForTheBookingUseCase)
    def saveBookingGateway = Mock(SaveBookingGateway)
    def findBookingByIdUseCase = Mock(FindBookingByIdUseCase)

    def useCase = new UpdateBookingUseCase(isThereAvailabilityForTheBookingUseCase, saveBookingGateway, findBookingByIdUseCase)

    def "It should update the dates and guest(s) details with success"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The existent booker, who made the booking before"
        def booker = BookerFixture.create(id : UUID.randomUUID())
        and : "than, the selected room"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "selected period"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, the created booking"
        def roomBooking = RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
        def booking = create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomBookings: [roomBooking]
        ])

        and : "the booker would like to update the checkin, checkout dates and the guest data"
        def updatableRoomBookProperties = UpdatableRoomBookingPropertiesFixture.create([
                id : roomBooking.id,
                guestName : "Ciclana de Tal",
                guestEmail : "ciclana.tal@gmail.com"
        ])
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create([
                checkinDate: LocalDate.now().plusMonths(1),
                checkoutDate : LocalDate.now().plusMonths(2),
                roomBookings: [updatableRoomBookProperties]
        ])


        when : "use case is called"
        Booking updated = useCase.execute(booking.id, updatableBookProperties)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            true
        }

        and : "the process of creation booking should be executed with success"
        1 * saveBookingGateway.execute(booking) >> {
            create([
                    id : booking.id,
                    booker : booker,
                    checkinDate: updatableBookProperties.checkinDate,
                    checkoutDate: updatableBookProperties.checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomBookings: [RoomBookingFixture.create(
                            id : roomBooking.id,
                            room : selectedRoom,
                            guestName: updatableRoomBookProperties.guestName,
                            guestEmail: updatableRoomBookProperties.guestEmail
                    )]
            ])
        }

        and : "Booking should be returned"
        null != updated

        and : "It must carry id within"
        null != updated.id

        and : "its status must be the expected"
        updated.status == PENDING

        and : "the dates of checkin and checkout should be updated ones"
        updated.checkinDate == LocalDate.now().plusMonths(1)
        updated.checkoutDate == LocalDate.now().plusMonths(2)

        and : "the room booking guest details should be updated"
        updated.roomBookings[0].id == roomBooking.id
        updated.roomBookings[0].guestName == updatableRoomBookProperties.guestName
        updated.roomBookings[0].guestEmail == updatableRoomBookProperties.guestEmail

        and : "the other booking properties should stay as they were"
        updated.id == booking.id
        updated.numberOfAdults == booking.numberOfAdults
        updated.numberOfChildren == booking.numberOfChildren
        updated.booker == booker
        updated.totalAmount == booking.totalAmount
    }

    def "It should update only the dates of the booking with success"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The existent booker, who made the booking before"
        def booker = BookerFixture.create(id : UUID.randomUUID())
        and : "than, the selected room"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "selected period"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, the created booking"
        def roomBooking = RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
        def booking = create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomBookings: [roomBooking]
        ])

        and : "the booker would like to update only the checkin and checkout dates"
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create([
                checkinDate: LocalDate.now().plusMonths(1),
                checkoutDate : LocalDate.now().plusMonths(2),
                roomBookings: null
        ])


        when : "use case is called"
        Booking updated = useCase.execute(booking.id, updatableBookProperties)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            true
        }

        and : "the process of creation booking should be executed with success"
        1 * saveBookingGateway.execute(booking) >> {
            create([
                    id : booking.id,
                    booker : booker,
                    checkinDate: updatableBookProperties.checkinDate,
                    checkoutDate: updatableBookProperties.checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomBookings: [roomBooking]
            ])
        }

        and : "Booking should be returned"
        null != updated

        and : "It must carry id within"
        null != updated.id

        and : "its status must be the expected"
        updated.status == PENDING

        and : "the dates of checkin and checkout should be updated ones"
        updated.checkinDate == LocalDate.now().plusMonths(1)
        updated.checkoutDate == LocalDate.now().plusMonths(2)

        and : "the room booking guest details should not be updated"
        updated.roomBookings[0].id == roomBooking.id
        updated.roomBookings[0].guestName == roomBooking.guestName
        updated.roomBookings[0].guestEmail == roomBooking.guestEmail

        and : "the other booking properties should stay as they were"
        updated.id == booking.id
        updated.numberOfAdults == booking.numberOfAdults
        updated.numberOfChildren == booking.numberOfChildren
        updated.booker == booker
        updated.totalAmount == booking.totalAmount
    }

    def "It should update only the guest details of the booking with success"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The existent booker, who made the booking before"
        def booker = BookerFixture.create(id : UUID.randomUUID())
        and : "than, the selected room"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "selected period"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, the created booking"
        def roomBooking = RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
        def booking = create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                roomBookings: [roomBooking]
        ])

        and : "the booker would like to update only the guest data"
        def updatableRoomBookProperties = UpdatableRoomBookingPropertiesFixture.create([
                id : roomBooking.id,
                guestName : "Ciclana de Tal",
                guestEmail : "ciclana.tal@gmail.com"
        ])
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create([
                checkinDate: null,
                checkoutDate : null,
                roomBookings: [updatableRoomBookProperties]
        ])


        when : "use case is called"
        Booking updated = useCase.execute(booking.id, updatableBookProperties)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            true
        }

        and : "the process of creation booking should be executed with success"
        1 * saveBookingGateway.execute(booking) >> {
            create([
                    id : booking.id,
                    booker : booker,
                    checkinDate: booking.checkinDate,
                    checkoutDate: booking.checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomBookings: [RoomBookingFixture.create(
                            id : roomBooking.id,
                            room : selectedRoom,
                            guestName: updatableRoomBookProperties.guestName,
                            guestEmail: updatableRoomBookProperties.guestEmail
                    )]
            ])
        }

        and : "Booking should be returned"
        null != updated

        and : "It must carry id within"
        null != updated.id

        and : "its status must be the expected"
        updated.status == PENDING

        and : "the dates of checkin and checkout should not be updated ones"
        updated.checkinDate == LocalDate.now()
        updated.checkoutDate == LocalDate.now().plusMonths(1)

        and : "the room booking guest details should be updated"
        updated.roomBookings[0].id == roomBooking.id
        updated.roomBookings[0].guestName == updatableRoomBookProperties.guestName
        updated.roomBookings[0].guestEmail == updatableRoomBookProperties.guestEmail

        and : "the other booking properties should stay as they were"
        updated.id == booking.id
        updated.numberOfAdults == booking.numberOfAdults
        updated.numberOfChildren == booking.numberOfChildren
        updated.booker == booker
        updated.totalAmount == booking.totalAmount
    }

    def "It should throw an exception because you can not inform null id to update one booking"(){
        given : "null id"
        def id = null

        and : "valid new data to update"
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create()

        when : "use case is called"
        useCase.execute(id, updatableBookProperties)

        then : "find booking by id process should not be called"
        0 * findBookingByIdUseCase.execute(id)

        and : "checking availability process should not be called"
        0 * isThereAvailabilityForTheBookingUseCase.execute(_ as Booking)

        and : "the process of creation booking should not be called"
        0 * saveBookingGateway.execute(_ as Booking)

        and : "throw an exception telling the id of the booking must be informed"
        def e = thrown(IllegalArgumentException)
        e.message == "You must inform the id to update a booking properties"
    }

    def "It should throw an exception because there is no booking with given id"(){
        given : "a non-existent booking id"
        def id = UUID.randomUUID()

        and : "valid new data to update"
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create()

        when : "use case is called"
        useCase.execute(id, updatableBookProperties)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(id) >> {
            Optional.empty()
        }

        and : "checking availability process should not be called"
        0 * isThereAvailabilityForTheBookingUseCase.execute(_ as Booking)

        and : "the process of creation booking should not be called"
        0 * saveBookingGateway.execute(_ as Booking)

        and : "throw an exception telling there is no booking with given id"
        def e = thrown(UseCaseException)
        e.message == "You can not update a non existent booking"
    }

    def "It should throw an exception because the booking is not in PENDING status"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The existent booker, who made the booking before"
        def booker = BookerFixture.create(id : UUID.randomUUID())
        and : "than, the selected room"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "selected period"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, the created booking"
        def roomBooking = RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
        def booking = create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : PAID,
                roomBookings: [roomBooking]
        ])

        and : "the booker would like to update only guest data"
        def updatableRoomBookProperties = UpdatableRoomBookingPropertiesFixture.create([
                id : roomBooking.id,
                guestName : "Ciclana de Tal",
                guestEmail : "ciclana.tal@gmail.com"
        ])
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create([
                checkinDate: null,
                checkoutDate : null,
                roomBookings: [updatableRoomBookProperties]
        ])

        when : "use case is called"
        useCase.execute(booking.id, updatableBookProperties)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "checking availability process should not be called"
        0 * isThereAvailabilityForTheBookingUseCase.execute(_ as Booking)

        and : "the process of creation booking should not be called"
        0 * saveBookingGateway.execute(_ as Booking)

        and : "throw an exception telling that is not allowed to update booking in different statuses of PENDING"
        def e = thrown(UseCaseException)
        e.message == "You can only update no-confirmed bookings, if you already paid, and would like to change, please, cancel this one and make a new booking. Or if you canceled, please, create a new one."
    }

    def "It should throw an exception because there is no availability for the new period of the booking"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The existent booker, who made the booking before"
        def booker = BookerFixture.create(id : UUID.randomUUID())
        and : "than, the selected room"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "selected period"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "than, the created booking"
        def roomBooking = RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
        def booking = create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : PENDING,
                roomBookings: [roomBooking]
        ])

        and : "the booker would like to update the checkin, checkout dates and the guest data"
        def updatableRoomBookProperties = UpdatableRoomBookingPropertiesFixture.create([
                id : roomBooking.id,
                guestName : "Ciclana de Tal",
                guestEmail : "ciclana.tal@gmail.com"
        ])
        UpdatableBookingProperties updatableBookProperties = UpdatableBookingPropertiesFixture.create([
                checkinDate: LocalDate.now().plusMonths(1),
                checkoutDate : LocalDate.now().plusMonths(2),
                roomBookings: [updatableRoomBookProperties]
        ])

        when : "use case is called"
        useCase.execute(booking.id, updatableBookProperties)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "checking availability process should not be called"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            false
        }

        and : "the process of creation booking should not be called"
        0 * saveBookingGateway.execute(booking)

        and : "throw an exception telling there is no availability for the new range of the booking"
        def e = thrown(UseCaseException)
        e.message == "There is no availability for the room(s) " + booking.roomBookings + " within the period " + booking.checkinDate + " up to " + booking.checkoutDate
    }
}
