package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.domain.Hotel
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.SaveBookingGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PENDING


class CreateBookingUseCaseSpec extends Specification {

    def isThereAvailabilityForTheBookingUseCase = Mock(IsThereAvailabilityForTheBookingUseCase)
    def saveBookingGateway = Mock(SaveBookingGateway)
    def useCase = new CreateBookingUseCase(isThereAvailabilityForTheBookingUseCase, saveBookingGateway)

    def "It should create booking for a room in a Hotel with success"(){
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
        Booking reserved = useCase.execute(booking)

        then : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            true
        }

        and : "the process of creation booking should be executed with success"
        1 * saveBookingGateway.execute(booking) >> {
            BookingFixture.create([
                    id : UUID.randomUUID(),
                    booker : BookerFixture.create(id : UUID.randomUUID()),
                    checkinDate: checkinDate,
                    checkoutDate: checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomBookings: [RoomBookingFixture.create(id : UUID.randomUUID(), room : selectedRoom)]
            ])
        }

        and : "Booking should be returned"
        null != reserved
        and : "It must carry id within"
        null != reserved.id
        and : "its status must be the expected"
        booking.status == PENDING
    }

    def "It should throw an exception because there is no availability for the room in a specific date"(){
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
        useCase.execute(booking)

        then : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            false
        }

        and : "the process of creation booking should not be executed"
        0 * saveBookingGateway.execute(booking)

        and : "the use case should throw an expcetion"
        def e = thrown(UseCaseException)
        and : "exception should not be null"
        null != e
        and : "its message should be the expected"
        e.message == "There is no availability for this period and " +
                "room(s) " + booking.getRoomBookings() + " " +
                booking.getCheckinDate() + " up to " + booking.getCheckoutDate()
    }
}
