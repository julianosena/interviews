package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.gateway.SaveBlockGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class UpdateBlockUseCase {

    private final FindBlockByIdUseCase findBlockByIdUseCase;
    private final IsThereAvailabilityForTheBlockUseCase isThereAvailabilityForTheBlockUseCase;
    private final SaveBlockGateway saveBlockGateway;

    public Block execute(final Block block){
        Assert.notNull(block.getId(), "You must inform the id to update a block");

        this.findBlockByIdUseCase.execute(block.getId())
                .orElseThrow(() -> new UseCaseException("You can not update a non existent block"));

        if(isThereAvailabilityForTheBlockUseCase.execute(block)){
            return saveBlockGateway.execute(block);
        }

        throw new UseCaseException("There is no availability to update the block for the given period");
    }

}
