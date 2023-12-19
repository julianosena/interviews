package com.interview.application.gateway.database.h2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKING")
public class BookingH2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "booker_id")
    private BookerH2 booker;

    @Column(name = "checkin_date")
    private LocalDate checkinDate;

    @Column(name = "checkout_date")
    private LocalDate checkoutDate;

    @Column(name = "number_of_adults")
    private Long numberOfAdults;

    @Column(name = "number_of_children")
    private Long numberOfChildren;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomBookingH2> roomBookings;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "previous_booking_id")
    private BookingH2 previousBooking;

    public BigDecimal getTotalAmount() {
        if (null == this.totalAmount) {
            this.totalAmount = this.roomBookings.stream().map(roomBooking -> {
                BigDecimal adultsRate = roomBooking.getRoom().getType().getRateAdult().multiply(new BigDecimal(this.numberOfAdults));
                BigDecimal childrenRate = roomBooking.getRoom().getType().getRateChildren().multiply(new BigDecimal(this.numberOfChildren));
                return adultsRate.add(childrenRate);
            }).reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return this.totalAmount;
    }

    public enum Status {
        PENDING, CANCELED, PAID, REFUNDED
    }
}
