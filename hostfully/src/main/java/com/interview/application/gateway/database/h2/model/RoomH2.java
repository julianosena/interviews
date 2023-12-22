package com.interview.application.gateway.database.h2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROOM")
public class RoomH2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelH2 hotel;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomTypeH2 type;

    @Column(name = "floor")
    private String floor;

    @Column(name = "number")
    private String number;

    @Column(name = "max_occupancy")
    private Long maxOccupancy;

    @Column(name = "is_available")
    private boolean available;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "room_room_attribute",
        joinColumns = {@JoinColumn(name = "room_id")},
        inverseJoinColumns = {@JoinColumn(name = "room_attribute_id")}
    )
    private List<RoomAttributeH2> attributes;

    @OneToMany(mappedBy = "room")
    private List<RoomBookingH2> roomBookings;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}