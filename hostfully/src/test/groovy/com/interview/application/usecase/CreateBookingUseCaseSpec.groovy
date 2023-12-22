package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.domain.Hotel
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.CreateBookingGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

import java.time.LocalDate

import static com.interview.application.domain.Booking.Status.PENDING


class CreateBookingUseCaseSpec extends Specification {

    def doesTheBookingHaveDuplicatedRoomsUseCase = Mock(DoesTheBookingHaveDuplicatedRoomsUseCase)
    def doTheRoomsSupportTotalGuestsAmountUseCase = Mock(DoTheRoomsSupportTotalGuestsAmountUseCase)
    def isThereAvailabilityForTheBookingUseCase = Mock(IsThereAvailabilityForTheBookingUseCase)
    def createBookingGateway = Mock(CreateBookingGateway)
    def useCase = new CreateBookingUseCase(doesTheBookingHaveDuplicatedRoomsUseCase, doTheRoomsSupportTotalGuestsAmountUseCase, isThereAvailabilityForTheBookingUseCase, createBookingGateway)

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

        then : "does the booking have duplicated rooms process should be called and return false"
        1 * doesTheBookingHaveDuplicatedRoomsUseCase.execute(booking) >> {
            false
        }

        and : "do the rooms support total guests amount process should be called and return true"
        1 * doTheRoomsSupportTotalGuestsAmountUseCase.execute(booking) >> {
            true
        }

        and : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            true
        }

        and : "the process of creation booking should be executed with success"
        1 * this.createBookingGateway.execute(booking) >> {
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

        then : "does the booking have duplicated rooms process should be called and return false"
        1 * doesTheBookingHaveDuplicatedRoomsUseCase.execute(booking) >> {
            false
        }

        and : "do the rooms support total guests amount process should be called and return true"
        1 * doTheRoomsSupportTotalGuestsAmountUseCase.execute(booking) >> {
            true
        }

        and : "checking availability process should return that there is availability for this booking"
        1 * isThereAvailabilityForTheBookingUseCase.execute(booking) >> {
            false
        }

        and : "the process of creation booking should not be executed"
        0 * this.createBookingGateway.execute(booking)

        and : "the use case should throw an expcetion"
        def e = thrown(UseCaseException)
        and : "exception should not be null"
        null != e
        and : "its message should be the expected"
        e.message == "There is no availability for the selected rooms within the given period " +
                booking.getCheckinDate() + " up to " + booking.getCheckoutDate()
    }
}
