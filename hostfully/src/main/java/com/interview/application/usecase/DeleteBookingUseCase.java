package com.interview.application.usecase;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.DeleteBookingGateway;
import com.interview.application.usecase.exception.UseCaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteBookingUseCase {

    private final FindBookingByIdUseCase findBookingByIdUseCase;
    private final DoesTheBookingHaveParentBookingUseCase doesTheBookingHaveParentBookingUseCase;
    private final DeleteBookingGateway deleteBookingGateway;

    public void execute(final UUID id){
        Assert.notNull(id, "You must inform the id to delete the booking");

        Booking booking = this.findBookingByIdUseCase.execute(id)
                .orElseThrow(() -> new UseCaseException("You can not delete a non existent booking"));

        if(doesTheBookingHaveParentBookingUseCase.execute(booking))
            throw new UseCaseException("You can not delete a canceled booking that belongs to another one, probably, It was canceled and re-booked");

        deleteBookingGateway.execute(booking);
    }

}
