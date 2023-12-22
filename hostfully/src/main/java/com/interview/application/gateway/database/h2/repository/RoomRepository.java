package com.interview.application.gateway.database.h2.repository;

import com.interview.application.gateway.database.h2.model.RoomH2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<RoomH2, UUID> {
    List<RoomH2> findByIdIn(List<UUID> ids);
}
