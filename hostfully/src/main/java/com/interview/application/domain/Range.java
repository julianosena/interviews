package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Range {

    private LocalDate start;
    private LocalDate end;

}
