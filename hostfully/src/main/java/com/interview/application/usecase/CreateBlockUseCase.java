package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.gateway.SaveBlockGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class CreateBlockUseCase {

    private final IsThereAvailabilityForTheBlockUseCase isThereAvailabilityForTheBlockUseCase;
    private final SaveBlockGateway saveBlockGateway;

    public Block execute(final Block block){
        Assert.isNull(block.getId(), "You must not inform the id to create a block");

        if(isThereAvailabilityForTheBlockUseCase.execute(block)){
            return saveBlockGateway.execute(block);
        }

        throw new UseCaseException("There is availability to create the block for the given period");
    }

}
