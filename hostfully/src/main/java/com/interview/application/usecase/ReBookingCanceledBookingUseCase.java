package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.domain.RoomBooking;
import com.interview.application.domain.mapper.ReCreateBookingMapper;
import com.interview.application.domain.mapper.ReCreateRoomBookingMapper;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.UUID;

import static com.interview.application.domain.Booking.Status.CANCELED;

@Component
@RequiredArgsConstructor
public class ReBookingCanceledBookingUseCase {

    private final FindBookingByIdUseCase findBookingByIdUseCase;
    private final CreateBookingUseCase createBookingUseCase;

    public Booking execute(final UUID id){
        Assert.notNull(id, "You must inform the id for re-booking process");

        final Booking booking = this.findBookingByIdUseCase.execute(id)
                .orElseThrow(() -> new UseCaseException("You can not re-book a non existent canceled booking"));

        if(booking.getStatus() != CANCELED) throw new UseCaseException("You can not re book a non-canceled booking");

        Booking reCreatedBooking = Mappers.getMapper(ReCreateBookingMapper.class).map(booking);
        reCreatedBooking.setRoomBookings(new ArrayList<>());
        for (RoomBooking roomBooking : booking.getRoomBookings()) {
            RoomBooking reCreatedRoomBooking = Mappers.getMapper(ReCreateRoomBookingMapper.class).map(roomBooking);
            reCreatedBooking.getRoomBookings().add(reCreatedRoomBooking);
        }
        reCreatedBooking.setPreviousBooking(booking);

        return createBookingUseCase.execute(booking);
    }
}
