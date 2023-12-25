package com.interview.application.usecase

import com.interview.application.domain.fixture.BlockFixture
import com.interview.application.gateway.FindBlockByIdGateway
import spock.lang.Specification

class FindBlockByIdUseCaseSpec extends Specification {

    def findBlockByIdGateway = Mock(FindBlockByIdGateway)
    def findBlockByIdUseCase = new FindBlockByIdUseCase(findBlockByIdGateway)

    def "It should find a block by its id with success"(){
        given: "Valid and existent block"
        def block = BlockFixture.create()

        when: "use case is called"
        def optional = findBlockByIdUseCase.execute(block.id)

        then: "find block process should be called once and return a valid existent block"
        1 * findBlockByIdUseCase.execute(block.id) >> {
            Optional.of(block)
        }

        and : "optional as response should not be null or empty"
        null != optional
        !optional.isEmpty()
    }
}
