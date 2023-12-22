package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.domain.Room;
import com.interview.application.domain.RoomBooking;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DoTheRoomsSupportTotalGuestsAmountUseCase {

    private final FindRoomsByIdsUseCase findRoomsByIdsUseCase;

    public boolean execute(Booking booking){
        List<UUID> roomIds = booking.getRoomBookings().stream().map(RoomBooking::getRoom).map(Room::getId).toList();
        List<Room> rooms = findRoomsByIdsUseCase.execute(roomIds, Room::isAvailable);

        if(rooms.isEmpty()) throw new UseCaseException("The selected rooms don't exist, check your solicitation and try again");

        long totalAmountOfGuests = booking.getTotalAmountOfGuests();
        long totalAmountOfOccupancy = rooms.stream().mapToLong(Room::getMaxOccupancy).sum();

        return totalAmountOfGuests <= totalAmountOfOccupancy;
    }
}