package com.interview.application.gateway;

import com.interview.application.domain.Block;

import java.time.LocalDate;
import java.util.List;

public interface FindBlocksByRangeGateway {

    List<Block> execute(LocalDate start, LocalDate end);

}
