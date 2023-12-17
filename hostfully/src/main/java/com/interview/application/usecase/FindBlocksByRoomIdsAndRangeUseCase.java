package com.interview.application.usecase;

import com.interview.application.domain.Block;
import com.interview.application.domain.Range;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBlocksByRoomIdsAndRangeUseCase {

    public List<Block> execute(List<UUID> ids, Range range){
        return Collections.emptyList();
    }

}
