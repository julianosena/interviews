package com.interview.application.gateway.database.h2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROOM_BOOKING")
public class RoomBookingH2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_booking_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingH2 booking;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomH2 room;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_email")
    private String guestEmail;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
