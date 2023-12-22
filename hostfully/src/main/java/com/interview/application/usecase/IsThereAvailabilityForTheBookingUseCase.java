package com.interview.application.usecase;

import com.interview.application.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IsThereAvailabilityForTheBookingUseCase {

    private final FindBookingsByRoomIdsAndRangeUseCase findBookingsByRoomIdsAndRangeUseCase;
    private final FindBlocksByRangeUseCase findBlocksByRangeUseCase;
    private final FindRoomsByIdsUseCase findRoomsByIdsUseCase;

    public boolean execute(final Booking booking) {

        List<UUID> ids = booking.getRoomBookings()
                .stream()
                .map(RoomBooking::getRoom)
                .map(Room::getId)
                .toList();

        List<Room> rooms = findRoomsByIdsUseCase.execute(ids, Room::isAvailable);
        if(ids.size() != rooms.size()) return false;

        Range range = Range.builder()
                .start(booking.getCheckinDate())
                .end(booking.getCheckoutDate())
                .build();

        List<Block> blocks = findBlocksByRangeUseCase.execute(range);
        if(!CollectionUtils.isEmpty(blocks)){
            return false;
        }

        List<Booking> bookings = findBookingsByRoomIdsAndRangeUseCase.execute(ids, booking.getCheckinDate(), booking.getCheckoutDate())
                .stream()
                .filter(item -> !item.getStatus().equals(Booking.Status.CANCELED))
                .filter(item -> !item.getStatus().equals(Booking.Status.REFUNDED))
                .toList();

        if(null != booking.getId() && bookings.size() == 1){
            UUID reservedId = bookings.iterator().next().getId();
            return reservedId.equals(booking.getId());
        }

        return CollectionUtils.isEmpty(bookings);
    }
}