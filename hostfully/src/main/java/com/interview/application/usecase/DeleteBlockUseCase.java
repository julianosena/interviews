package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.gateway.DeleteBlockGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteBlockUseCase {

    private final FindBlockByIdUseCase findBlockByIdUseCase;
    private final DeleteBlockGateway deleteBlockGateway;

    public void execute(final UUID id){
        Assert.notNull(id, "You must inform the id to delete the block");

        Block block = this.findBlockByIdUseCase.execute(id)
                .orElseThrow(() -> new UseCaseException("You can not delete a non existent block"));

        deleteBlockGateway.execute(block);
    }

}
