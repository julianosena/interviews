package com.interview.application.usecase


import com.interview.application.domain.Range
import com.interview.application.domain.fixture.BlockFixture
import spock.lang.Specification

class IsThereAvailabilityForTheBlockUseCaseSpec extends Specification {

    def findBlocksByRangeUseCase = Mock(FindBlocksByRangeUseCase)
    def useCase = new IsThereAvailabilityForTheBlockUseCase(findBlocksByRangeUseCase)

    def "It should return true, because, there is availability for the requested block"(){
        given: "valid block"
        def block = BlockFixture.create()

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(block)

        then : "finding block within a range process should be called and dont return any block"
        1 * findBlocksByRangeUseCase.execute(_ as Range) >> {
            []
        }

        and : "there is availability to make the given booking"
        isThereAvailability
    }

    def "It should return false, because, there is more than one block, within the given range of dates"(){
        given: "valid block"
        def block = BlockFixture.create()

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(block)

        then : "finding block within a range process should be called and return list of blocks"
        1 * findBlocksByRangeUseCase.execute(_ as Range) >> {
            [block, BlockFixture.create()]
        }

        and : "there is no availability to the given block"
        !isThereAvailability
    }

    def "It should return true, because, there is only one block that is the same of the given one, within the given range of dates"(){
        given: "valid block"
        def block = BlockFixture.create()

        when : "use case is called"
        boolean isThereAvailability = useCase.execute(block)

        then : "finding block within a range process should be called and return list with same given block"
        1 * findBlocksByRangeUseCase.execute(_ as Range) >> {
            [block]
        }

        and : "there is availability to create the block"
        isThereAvailability
    }
}
