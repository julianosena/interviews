package com.interview.application.usecase

import com.interview.application.domain.Block
import com.interview.application.domain.fixture.BlockFixture
import com.interview.application.gateway.SaveBlockGateway
import com.interview.application.usecase.exception.UseCaseException
import spock.lang.Specification

class UpdateBlockUseCaseSpec extends Specification {

    def findBlockByIdUseCase = Mock(FindBlockByIdUseCase)
    def isThereAvailabilityForTheBlockUseCase = Mock(IsThereAvailabilityForTheBlockUseCase)
    def saveBlockGateway = Mock(SaveBlockGateway)

    def useCase = new UpdateBlockUseCase(findBlockByIdUseCase, isThereAvailabilityForTheBlockUseCase, saveBlockGateway)

    def "It should update block with success"(){
        given: "Valid and existent block"
        def block = BlockFixture.create()

        when : "use case is called"
        Block updated = useCase.execute(block)

        then : "finding block by id process should be called and find a block to update"
        1 * findBlockByIdUseCase.execute(block.id) >> {
            Optional.of(BlockFixture.create())
        }

        and : "checking availability process should return that there is availability for this block"
        1 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            true
        }

        and : "the process of save block should be executed with success"
        1 * saveBlockGateway.execute(block) >> {
            BlockFixture.create()
        }

        and : "Block should be returned"
        null != updated

        and : "It must carry id within"
        null != updated.id
    }

    def "It should throw an exception because you can not inform null id to update one block"(){
        given: "Valid and existent hotel"
        def block = BlockFixture.create([ id : null ])

        when : "use case is called"
        useCase.execute(block)

        then : "finding block by id process should not be called"
        0 * findBlockByIdUseCase.execute(block.id)

        and : "checking availability process should not be called"
        0 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            true
        }

        and : "the process of save block should not be called"
        0 * saveBlockGateway.execute(block) >> {
            BlockFixture.create()
        }

        and : "throw an exception telling the id of the block must be informed"
        def e = thrown(IllegalArgumentException)
        e.message == "You must inform the id to update a block"
    }

    def "It should throw an exception because there is no block with given id"(){
        given: "Valid and existent block"
        def block = BlockFixture.create()

        when : "use case is called"
        useCase.execute(block)

        then : "finding block by id process should be called and doesn't return any block"
        1 * findBlockByIdUseCase.execute(block.id) >> {
            Optional.empty()
        }

        and : "checking availability process should not be called"
        0 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            true
        }

        and : "the process of save block should not be called"
        0 * saveBlockGateway.execute(block) >> {
            BlockFixture.create()
        }

        and : "throw an exception telling there is no block with given id"
        def e = thrown(UseCaseException)
        e.message == "You can not update a non existent block"
    }

    def "It should throw an exception because there is no availability for the new period of the block"(){
        given: "Valid and existent block"
        def block = BlockFixture.create()

        when : "use case is called"
        useCase.execute(block)

        then : "finding block by id process should be called and find a block to update"
        1 * findBlockByIdUseCase.execute(block.id) >> {
            Optional.of(BlockFixture.create())
        }

        and : "checking availability process should return that there is availability for this block"
        1 * isThereAvailabilityForTheBlockUseCase.execute(block) >> {
            false
        }

        and : "the process of saving block should not be called"
        0 * saveBlockGateway.execute(block)

        and : "throw an exception telling there is no availability for the new range of the block"
        def e = thrown(UseCaseException)
        e.message == "There is no availability to update the block for the given period"
    }
}
