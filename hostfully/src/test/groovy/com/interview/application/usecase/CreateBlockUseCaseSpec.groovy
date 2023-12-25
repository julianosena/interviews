package com.interview.application.usecase

import com.interview.application.domain.fixture.BlockFixture
import com.interview.application.gateway.SaveBlockGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

class CreateBlockUseCaseSpec extends Specification {

    def isThereAvailabilityForTheBlockUseCase = Mock(IsThereAvailabilityForTheBlockUseCase)
    def saveBlockGateway = Mock(SaveBlockGateway)
    def creatBlockUseCase = new CreateBlockUseCase(isThereAvailabilityForTheBlockUseCase, saveBlockGateway)

    def "It should create block with success"(){
        given: "Valid non-existent block"
        def block = BlockFixture.create([ id : null ])

        when: "use case is called"
        block = creatBlockUseCase.execute(block)

        then: "is there availability for the block process should return true"
        1 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            true
        }

        and: "save block process should be called and return saved block"
        1 * saveBlockGateway.execute(block) >> {
            BlockFixture.create([
                    start : block.start,
                    end : block.end,
                    createdAt: block.createdAt,
                    updatedAt: block.updatedAt
            ])
        }

        and : "returned block must not be null"
        null != block

        and : "its properties must be filled even the id"
        null != block.id
        null != block.start
        null != block.end
        null != block.createdAt
        null != block.updatedAt
    }

    def "It should throw an exception because there is no availability for the given block"(){
        given: "Valid non-existent block"
        def block = BlockFixture.create([ id : null ])

        when: "use case is called"
        block = creatBlockUseCase.execute(block)

        then: "is there availability for the block process should return true"
        1 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            false
        }

        and: "save block process should not be called"
        0 * saveBlockGateway.execute(block)

        and : "returned block must not be null"
        def e = thrown(UseCaseException)
        and : "exception should not be null"
        null != e
        and : "its message should be the expected"
        e.message == "There is no availability to create a block for the given period"
    }

    def "It should throw an exception because you are informing an id to create new block, It is not allowed"(){
        given: "Valid non-existent block"
        def block = BlockFixture.create()

        when: "use case is called"
        block = creatBlockUseCase.execute(block)

        then: "is there availability for the block process should not be called"
        0 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            false
        }

        and: "save block process should not be called"
        0 * saveBlockGateway.execute(block)

        and : "exception should not be null"
        def e = thrown(IllegalArgumentException)
        null != e
        and : "its message should be the expected"
        e.message == "You must not inform the id to create a block"
    }
}
