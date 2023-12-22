package com.interview.application.domain;

import com.interview.application.domain.exception.BusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.interview.application.domain.Booking.Status.CANCELED;
import static com.interview.application.domain.Booking.Status.PAID;

@Getter
@Setter
@Builder
public class Booking {

    private UUID id;
    private Booker booker;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Long numberOfAdults;
    private Long numberOfChildren;
    private List<RoomBooking> roomBookings;
    private BigDecimal totalAmount;
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
    private Booking previousBooking;

    public long getTotalAmountOfGuests(){
        return (numberOfAdults + numberOfChildren);
    }

    public boolean isAllowedToUpdate(){
        return (status.equals(Status.PENDING));
    }

    public boolean isAllowedToCancel(){
        return (status.equals(Status.PENDING) || status.equals(PAID));
    }

    public void cancel(){
        if(this.isAllowedToCancel()){
            this.status = CANCELED;
        } else {
            throw new BusinessException("You are allowed to cancel only pending or paid bookings");
        }
    }

    public enum Status {
        PENDING, CANCELED, PAID, REFUNDED
    }
}