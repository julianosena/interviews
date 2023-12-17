package com.interview.application.usecase

import com.interview.application.domain.Hotel
import com.interview.application.domain.Reservation
import com.interview.application.domain.fixture.*
import com.interview.application.gateway.CreateReservationGateway
import spock.lang.Specification

import java.time.LocalDate

class CreateReservationUseCaseSpec extends Specification {

    def isThereAvailabilityForTheReservationUseCase = Mock(IsThereAvailabilityForTheReservationUseCase)
    def createReservationGateway = Mock(CreateReservationGateway)
    def useCase = new CreateReservationUseCase(isThereAvailabilityForTheReservationUseCase, createReservationGateway)

    def "It should create reservation for a room in a Hotel with success"(){
        given: "Valid and existent hotel"
        Hotel hotel = HotelFixture.create()
        and : "its rooms."
        def rooms = RoomFixture.list(hotel)
        hotel.rooms = rooms

        and : "The booker would like to make a reservation"
        def booker = BookerFixture.create(id : null)
        and : "than, It selects a room from hotel"
        def selectedRoom = rooms.findAll { room -> room.type.name == "Standard" }.getFirst()

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
        Reservation reserved = useCase.execute(reservation)

        then : "checking availability process should return that there is availability for this reservation"
        1 * isThereAvailabilityForTheReservationUseCase.execute(reservation) >> {
            true
        }

        and : "the process of creation reservation should be executed with success"
        1 * createReservationGateway.execute(reservation) >> {
            ReservationFixture.create([
                    id : UUID.randomUUID(),
                    booker : BookerFixture.create(id : UUID.randomUUID()),
                    checkinDate: checkinDate,
                    checkoutDate: checkoutDate,
                    numberOfAdults: 2,
                    numberOfChildren: 1,
                    roomReservations: [RoomReservationFixture.create(id : UUID.randomUUID(), room : selectedRoom)]
            ])
        }

        and : "Reservation should be returned"
        null != reserved
        and : "It must carry id within"
        null != reserved.id
    }
}
