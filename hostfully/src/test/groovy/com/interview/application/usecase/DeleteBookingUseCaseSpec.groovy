package com.interview.application.usecase

import com.interview.application.domain.Booking
import com.interview.application.gateway.DeleteBookingGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

import static com.interview.application.domain.fixture.BookingFixture.create

class DeleteBookingUseCaseSpec extends Specification {

    def findBookingByIdUseCase = Mock(FindBookingByIdUseCase)
    def deleteBookingGateway = Mock(DeleteBookingGateway)
    def useCase = new DeleteBookingUseCase(findBookingByIdUseCase, deleteBookingGateway)

    def "It should delete a booking with success"(){
        given: "valid and existent booking"
        def booking = create([ id : UUID.randomUUID() ])

        when : "use case is called"
        useCase.execute(booking.id)

        then : "find booking by id process should return an existent one"
        1 * findBookingByIdUseCase.execute(booking.id) >> {
            Optional.of(booking)
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

        and : "the process of cancellation booking should not be called"
        0 * deleteBookingGateway.execute(_ as Booking)

        then : "should throw an exception telling that you can not delete a non existent booking"
        def e = thrown(UseCaseException)

        and : "message of the exception should be the expected"
        e.message == "You can not delete a non existent booking"
    }
}
