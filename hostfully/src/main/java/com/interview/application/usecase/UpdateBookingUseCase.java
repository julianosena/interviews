package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.domain.RoomBooking;
import com.interview.application.domain.UpdatableBookingProperties;
import com.interview.application.domain.UpdatableRoomBookingProperties;
import com.interview.application.domain.mapper.BookingMapper;
import com.interview.application.domain.mapper.RoomBookingMapper;
import com.interview.application.gateway.SaveBookingGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateBookingUseCase {

    private final IsThereAvailabilityForTheBookingUseCase isThereAvailabilityForTheBookingUseCase;
    private final SaveBookingGateway saveBookingGateway;
    private final FindBookingByIdUseCase findBookingByIdUseCase;

    public Booking execute(final UUID id, final UpdatableBookingProperties properties){
        Assert.notNull(id, "You must inform the id to update a booking properties");

        Booking booking = this.findBookingByIdUseCase.execute(id)
                .orElseThrow(() -> new UseCaseException("You can not update a non existent booking"));

        if(booking.isAllowedToUpdate()){
            Mappers.getMapper(BookingMapper.class).map(properties, booking);

            if(null != properties.getRoomBookings()) {
                for (UpdatableRoomBookingProperties updatableRoomBooking : properties.getRoomBookings()) {
                    RoomBooking roomBooking = booking.getRoomBookings()
                            .stream()
                            .filter(item -> item.getRoom().getId().equals(updatableRoomBooking.getRoomId()))
                            .filter(item -> item.getBooking().getId().equals(updatableRoomBooking.getBookingId()))
                            .findFirst()
                            .orElseThrow(() -> new UseCaseException("You can not update a non-existent room booking"));
                    Mappers.getMapper(RoomBookingMapper.class).map(updatableRoomBooking, roomBooking);
                }
            }

            if(isThereAvailabilityForTheBookingUseCase.execute(booking)){
                return saveBookingGateway.execute(booking);
            }

            throw new UseCaseException("There is no availability for " +
                    "the room(s) " + booking.getRoomBookings() + " " +
                    "within the period " + booking.getCheckinDate() + " up to " + booking.getCheckoutDate());
        }

        throw new UseCaseException("You can only update no-confirmed bookings, if you already paid, and would like to change, please, cancel this one and make a new booking. " +
                "Or if you canceled, please, create a new one.");
    }

}
