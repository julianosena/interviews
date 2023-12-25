package com.interview.application.usecase

import com.interview.application.domain.Range
import com.interview.application.domain.fixture.BlockFixture
import com.interview.application.gateway.FindBlocksByRangeGateway
import spock.lang.Specification

import java.time.LocalDate

class FindBlocksByRangeUseCaseSpec extends Specification {

    def findBlocksByRangeGateway = Mock(FindBlocksByRangeGateway)
    def findBlocksByRangeUseCase = new FindBlocksByRangeUseCase(findBlocksByRangeGateway)

    def "It should find block for the given range with success"(){
        given : "a valid range to find block within it"
        def range = Range.builder()
            .start(LocalDate.now())
            .end(LocalDate.now().plusMonths(1))
            .build()

        when : "use case is called"
        def blocks = findBlocksByRangeUseCase.execute(range)

        then : "find blocks by range process should be called and return list of blocks"
        findBlocksByRangeGateway.execute(range.start, range.end) >> {
            [BlockFixture.create()]
        }

        and : "the list should not be empty"
        !blocks.isEmpty()
    }

    def "It should not find block for the given range with success"(){
        given : "a valid range to find block within it"
        def range = Range.builder()
                .start(LocalDate.now())
                .end(LocalDate.now().plusMonths(1))
                .build()

        when : "use case is called"
        def blocks = findBlocksByRangeUseCase.execute(range)

        then : "find blocks by range process should be called and return an empty list of blocks"
        findBlocksByRangeGateway.execute(range.start, range.end) >> {
            []
        }

        and : "the list should be empty"
        blocks.isEmpty()
    }
}
