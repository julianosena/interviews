package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.domain.Range;
import com.interview.application.gateway.FindBlocksByRangeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindBlocksByRangeUseCase {

    private final FindBlocksByRangeGateway findBlocksByRangeGateway;

    public List<Block> execute(Range range){
        return findBlocksByRangeGateway.execute(range.getStart(), range.getEnd());
    }

}
