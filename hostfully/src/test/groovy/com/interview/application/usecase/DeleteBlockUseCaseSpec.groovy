package com.interview.application.usecase

import com.interview.application.domain.fixture.BlockFixture
import com.interview.application.gateway.DeleteBlockGateway
import com.interview.application.usecase.exception.NotFoundUseCaseException
import spock.lang.Specification

class DeleteBlockUseCaseSpec extends Specification {

    def findBlockByIdUseCase = Mock(FindBlockByIdUseCase)
    def deleteBlockGateway = Mock(DeleteBlockGateway)
    def deleteBlockUseCase = new DeleteBlockUseCase(findBlockByIdUseCase, deleteBlockGateway)

    def "It should delete block with success"(){
        given: "Valid and existent block"
        def block = BlockFixture.create()

        when: "use case is called"
        deleteBlockUseCase.execute(block.id)

        then: "find block to delete process should be called once and return a valid existent block"
        1 * findBlockByIdUseCase.execute(block.id) >> {
            Optional.of(block)
        }

        and: "delete block process should be called an run with success"
        1 * deleteBlockGateway.execute(block)
    }

    def "It should throw an exception because there is no an existent block for the given id"(){
        given: "Valid and existent block"
        def block = BlockFixture.create()

        when: "use case is called"
        deleteBlockUseCase.execute(block.id)

        then: "find block to delete process, should be called once and doesn't find any block with given id"
        1 * findBlockByIdUseCase.execute(block.id) >> {
            Optional.empty()
        }

        and: "delete block process should not be called"
        0 * deleteBlockGateway.execute(block)

        and: "it should throw an exception"
        def e = thrown(NotFoundUseCaseException)

        and : "message of the exception should be the expected"
        e.message == "You can not delete a non existent block"
    }

    def "It should throw an exception because you are not informing an id to delete a new block, It is not allowed"(){
        given: "Valid non-existent block"
        def block = BlockFixture.create([ id : null ])

        when: "use case is called"
        deleteBlockUseCase.execute(block.id)

        then: "is there availability for the block process should not be called"
        0 * findBlockByIdUseCase.execute(block)

        and: "delete block process should not be called"
        0 * deleteBlockGateway.execute(block)

        and : "returned block must not be null"
        def e = thrown(IllegalArgumentException)
        and : "exception should not be null"
        null != e
        and : "its message should be the expected"
        e.message == "You must inform the id to delete the block"
    }
}
