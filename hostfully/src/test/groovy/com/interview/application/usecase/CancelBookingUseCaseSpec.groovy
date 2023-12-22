package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.domain.Hotel
import com.interview.application.domain.exception.BusinessException
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.CreateBookingGateway
import com.interview.application.gateway.SaveBookingGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.*

class CancelBookingUseCaseSpec extends Specification {

    def findBookingByIdUseCase = Mock(FindBookingByIdUseCase)
    def saveBookingGateway = Mock(SaveBookingGateway)
    CancelBookingUseCase useCase = new CancelBookingUseCase(findBookingByIdUseCase, saveBookingGateway)

    def "It should cancel booking with success"(){
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
        Booking canceled = useCase.execute(booking.id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "the process of cancellation booking should be executed with success"
        1 * saveBookingGateway.execute(booking) >> {
            booking
        }

        then : "Booking should be returned"
        null != canceled

        and : "It must carry id within"
        null != canceled.id

        and : "its status must be the expected"
        canceled.status == CANCELED
    }

    def "It should throw an exception telling that you must inform an id to cancel"(){
        given: "Null booking id"
        def id = null

        when : "use case is called"
        useCase.execute(id)

        then : "find booking by id process should not be called"
        0 * findBookingByIdUseCase.execute(id)

        and : "the process of cancellation booking should not be called"
        0 * saveBookingGateway.execute(_ as Booking)

        then : "should throw an exception telling that you must inform an id to cancel"
        def e = thrown(IllegalArgumentException)

        and : "message of the exception should be the expected"
        e.message == "You must inform the id to cancel the booking"
    }

    def "It should throw an exception telling you can not cancel a non existent booking"(){
        given: "No existent booking id"
        def id = UUID.randomUUID()

        when : "use case is called"
        useCase.execute(id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(id) >> {
            Optional.empty()
        }

        and : "the process of cancellation booking should not be called"
        0 * saveBookingGateway.execute(_ as Booking)

        then : "should throw an exception telling you can not cancel a non existent booking"
        def e = thrown(UseCaseException)

        and : "message of the exception should be the expected"
        e.message == "You can not cancel a non existent booking"
    }

    def "It should throw an exception telling is not allowed to cancel non pending or paid bookings"(){
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
        def booking = BookingFixture.create([
                id : UUID.randomUUID(),
                booker : booker,
                checkinDate: checkinDate,
                checkoutDate: checkoutDate,
                numberOfAdults: 2,
                numberOfChildren: 1,
                status : REFUNDED,
                roomBookings: []
        ])
        def roomBooking = RoomBookingFixture.create(
                id : UUID.randomUUID(),
                room : selectedRoom,
                booking : booking
        )
        booking.roomBookings.add(roomBooking)

        when : "use case is called"
        useCase.execute(booking.id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "the process of cancellation booking should not be called"
        0 * saveBookingGateway.execute(booking)

        then : "should throw an exception telling is not allow to cancel non pending or paid booking"
        def e = thrown(BusinessException)

        and : "message of the exception should be the expected"
        e.message == "You are allowed to cancel only pending or paid bookings"
    }
}
