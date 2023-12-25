package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByRoomIdsAndRangeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindBookingsByRoomIdsAndRangeUseCase {

    private final FindBookingByRoomIdsAndRangeGateway findBookingByRoomIdsAndRangeGateway;

    public List<Booking> execute(final List<UUID> ids, final LocalDate checkinDate, final LocalDate checkoutDate){
        return findBookingByRoomIdsAndRangeGateway.execute(ids, checkinDate, checkoutDate);
    }

}
