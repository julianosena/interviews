package com.interview.application.gateway.database.h2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.uuid.UuidGenerator;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", type = UuidGenerator.class)
    @Column(name = "room_attribute_id")
    private UUID id;

    @Column(name = "description")
    private String description;
}
