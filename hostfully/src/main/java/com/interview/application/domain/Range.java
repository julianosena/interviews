package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString(of = { "start", "end" })
public class Range {

    private LocalDate start;
    private LocalDate end;

}
