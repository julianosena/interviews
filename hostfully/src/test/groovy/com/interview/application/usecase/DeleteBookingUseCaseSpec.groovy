package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.domain.fixture.BookingFixture
import com.interview.application.gateway.DeleteBookingGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

class DeleteBookingUseCaseSpec extends Specification {

    def findBookingByIdUseCase = Mock(FindBookingByIdUseCase)
    def doesTheBookingHasParentBookingUseCase = Mock(DoesTheBookingHaveParentBookingUseCase)
    def deleteBookingGateway = Mock(DeleteBookingGateway)
    def useCase = new DeleteBookingUseCase(findBookingByIdUseCase, doesTheBookingHasParentBookingUseCase, deleteBookingGateway)

    def "It should delete a booking with success"(){
        given: "valid and existent booking"
        def booking = BookingFixture.create([id: UUID.randomUUID() ])

        when : "use case is called"
        useCase.execute(booking.id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "does the booking has parent process should be called and return false"
        doesTheBookingHasParentBookingUseCase.execute(booking) >> {
            false
        }

        and : "the delete process should be executed with success"
        1 * deleteBookingGateway.execute(booking) >> {
            booking
        }
    }

    def "It should throw an exception telling that you must inform an id to delete"(){
        given: "Null booking id"
        def id = null

        when : "use case is called"
        useCase.execute(id)

        then : "find booking by id process should not be called"
        0 * findBookingByIdUseCase.execute(id)

        and : "does the booking has parent process should not be called"
        0 * doesTheBookingHasParentBookingUseCase.execute(_ as Booking)

        and : "the process of deletion booking should not be called"
        0 * deleteBookingGateway.execute(_ as Booking)

        then : "should throw an exception telling that you must inform an id to delete"
        def e = thrown(IllegalArgumentException)

        and : "message of the exception should be the expected"
        e.message == "You must inform the id to delete the booking"
    }

    def "It should throw an exception telling that you can not delete a non existent booking"(){
        given: "No existent booking id"
        def id = UUID.randomUUID()

        when : "use case is called"
        useCase.execute(id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(id) >> {
            Optional.empty()
        }

        and : "does the booking has parent process should not be called"
        0 * doesTheBookingHasParentBookingUseCase.execute(_ as Booking)

        and : "the process of cancellation booking should not be called"
        0 * deleteBookingGateway.execute(_ as Booking)

        then : "should throw an exception telling that you can not delete a non existent booking"
        def e = thrown(UseCaseException)

        and : "message of the exception should be the expected"
        e.message == "You can not delete a non existent booking"
    }

    def "It should throw an exception telling it cant be deleted because It belongs a history of cancellation and re-booking process"(){
        given: "valid and existent booking"
        def booking = BookingFixture.create([id: UUID.randomUUID() ])

        when : "use case is called"
        useCase.execute(booking.id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
        }

        and : "does the booking has parent process should be called and return true"
        doesTheBookingHasParentBookingUseCase.execute(booking) >> {
            true
        }

        and : "the process of cancellation booking should not be called"
        0 * deleteBookingGateway.execute(_ as Booking)

        then : "should throw an exception telling that it cant be deleted because It belongs a history of cancellation and re-booking process"
        def e = thrown(UseCaseException)

        and : "message of the exception should be the expected"
        e.message == "You can not delete a canceled booking that belongs to another one, probably, It was canceled and re-booked"
    }
}
