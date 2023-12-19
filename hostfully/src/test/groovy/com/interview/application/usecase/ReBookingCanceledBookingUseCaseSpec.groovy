package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.domain.Hotel
import com.interview.application.domain.UpdatableBookingProperties
import com.interview.application.domain.fixture.*
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.CANCELED
import static com.interview.application.domain.Booking.Status.PENDING

class ReBookingCanceledBookingUseCaseSpec extends Specification {

    def findBookingByIdUseCase = Mock(FindBookingByIdUseCase)
    def createBookingUseCase = Mock(CreateBookingUseCase)
    def useCase = new ReBookingCanceledBookingUseCase(findBookingByIdUseCase, createBookingUseCase)

    def "It should re-book canceled booking with success"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a booking"
        def booker = BookerFixture.create(id : UUID.randomUUID())
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.iterator().next()

        and : "select the period It would like to stay there"
        def checkinDate = LocalDate.now()
        def checkoutDate = checkinDate.plusMonths(1)

        and : "existent canceled booking"
        def roomBooking = RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
        def booking = BookingFixture.create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : CANCELED,
                roomBookings: [roomBooking]
        ])

        when : "use case is called"
        Booking reCreatedBooking = useCase.execute(booking.id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "create booking process should create a new booking"
        1 * createBookingUseCase.execute(_ as Booking) >> {
            BookingFixture.create([
                    id : UUID.randomUUID(),
                    booker : booking.booker,
                    checkinDate: booking.checkinDate,
                    checkoutDate: booking.checkoutDate,
                    numberOfAdults: booking.numberOfAdults,
                    numberOfChildren: booking.numberOfChildren,
                    status : PENDING,
                    roomBookings: [
                        RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)
                    ],
                    previousBooking : booking
            ])
        }

        and : "re-created booking should not be null"
        null != reCreatedBooking
        and : "its id be valid"
        null != reCreatedBooking.id
        and : "different from the canceled one"
        booking.id != reCreatedBooking.id

        and : "status should be PENDING, waiting for payment"
        reCreatedBooking.status == PENDING

        and : "previous booking should be set up"
        null != reCreatedBooking.previousBooking
        reCreatedBooking.previousBooking.id == booking.id

        and : "the rest of the data should be the same"
        booking.checkinDate == reCreatedBooking.checkinDate
        booking.checkoutDate == reCreatedBooking.checkoutDate
        booking.numberOfAdults == reCreatedBooking.numberOfAdults
        booking.numberOfChildren == reCreatedBooking.numberOfChildren
        booking.totalAmount == reCreatedBooking.totalAmount
    }

    def "It should throw an exception because you can not inform null id to re-book a canceled booking"(){
        given : "null id"
        def id = null

        when : "use case is called"
        useCase.execute(id)

        then : "find booking by id process should not be called"
        0 * findBookingByIdUseCase.execute(id)

        and : "create booking process should be called"
        0 * createBookingUseCase.execute(_ as Booking)

        and : "throw an exception telling the id of the booking must be informed"
        def e = thrown(IllegalArgumentException)
        e.message == "You must inform the id for re-booking process"
    }
}
