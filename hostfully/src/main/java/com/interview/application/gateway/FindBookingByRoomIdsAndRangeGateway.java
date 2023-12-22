package com.interview.application.gateway;

import com.interview.application.domain.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FindBookingByRoomIdsAndRangeGateway {

    List<Booking> execute(List<UUID> ids, LocalDate checkinDate, LocalDate checkoutDate);

}
