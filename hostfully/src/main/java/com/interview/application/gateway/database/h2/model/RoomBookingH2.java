package com.interview.application.gateway.database.h2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROOM_BOOKING")
public class RoomBookingH2 {

    @EmbeddedId
    private RoomBookingId id;

    @MapsId("bookingId")
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingH2 booking;

    @MapsId("roomId")
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomH2 room;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_email")
    private String guestEmail;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
