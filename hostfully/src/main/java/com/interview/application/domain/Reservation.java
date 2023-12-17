package com.interview.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Reservation {

    private UUID id;
    private Booker booker;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private Long numberOfAdults;
    private Long numberOfChildren;
    private List<RoomReservation> roomReservations;
    private BigDecimal totalAmount;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Status {
        PENDING, CANCELED, PAID, REJECTED, REFUNDED;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", booker=" + booker +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", roomReservations=" + roomReservations +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}