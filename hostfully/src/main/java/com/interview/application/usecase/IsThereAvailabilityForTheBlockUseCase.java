package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.domain.Range;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IsThereAvailabilityForTheBlockUseCase {

    private final FindBlocksByRangeUseCase findBlocksByRangeUseCase;

    public boolean execute(final Block block) {

        Range range = Range.builder()
                .start(block.getStart())
                .end(block.getEnd())
                .build();

        List<Block> blocks = findBlocksByRangeUseCase.execute(range);
        if(!CollectionUtils.isEmpty(blocks)){
            if(null != block.getId() && blocks.size() == 1){
                UUID reservedId = blocks.iterator().next().getId();
                return reservedId.equals(block.getId());
            }
        }

        return CollectionUtils.isEmpty(blocks);
    }
}