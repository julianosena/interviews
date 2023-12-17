package com.interview.application.usecase;

import com.interview.application.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IsThereAvailabilityForTheReservationUseCase {

    private final FindReservationsByRoomIdsAndRangeUseCase findReservationsByRoomIdsAndRangeUseCase;
    private final FindBlocksByRoomIdsAndRangeUseCase findBlocksByRoomIdsAndRangeUseCase;
    private final FindRoomsByIdsUseCase findRoomsByIdsUseCase;

    public boolean execute(final Reservation reservation) {

        List<UUID> ids = reservation.getRoomReservations()
                .stream()
                .map(RoomReservation::getRoom)
                .map(Room::getId)
                .toList();

        List<Room> rooms = findRoomsByIdsUseCase.execute(ids, Room::isAvailable);
        if(ids.size() != rooms.size()) return false;

        Range range = Range.builder()
                .start(reservation.getCheckinDate())
                .end(reservation.getCheckoutDate())
                .build();

        List<Block> blocks = findBlocksByRoomIdsAndRangeUseCase.execute(ids, range);
        if(!CollectionUtils.isEmpty(blocks)){
            return false;
        }

        List<Reservation> reservations = findReservationsByRoomIdsAndRangeUseCase.execute(ids, reservation.getCheckinDate(), reservation.getCheckoutDate());
        return CollectionUtils.isEmpty(reservations);
    }
}