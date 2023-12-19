package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBookingsByRoomIdsAndRangeUseCase {

    public List<Booking> execute(List<UUID> ids, LocalDate checkinDate, LocalDate checkoutDate){
        return Collections.emptyList();
    }

}
