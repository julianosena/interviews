package com.interview.application.gateway.database.h2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_attributes")
public class RoomAttributeH2 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_attribute_id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "attributes")
    private List<RoomH2> rooms;
}
