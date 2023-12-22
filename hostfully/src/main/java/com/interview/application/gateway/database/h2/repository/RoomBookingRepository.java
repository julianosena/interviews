package com.interview.application.gateway.database.h2.repository;

import com.interview.application.gateway.database.h2.model.RoomBookingH2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBookingH2, UUID> {
}
