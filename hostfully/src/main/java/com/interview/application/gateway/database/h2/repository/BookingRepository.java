package com.interview.application.gateway.database.h2.repository;

import com.interview.application.gateway.database.h2.model.BookingH2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingH2, UUID> {
}
