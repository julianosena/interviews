package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByIdGateway;
import com.interview.application.gateway.database.h2.model.mapper.BookingH2Mapper;
import com.interview.application.gateway.database.h2.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindBookingByIdH2Gateway implements FindBookingByIdGateway {

    private final BookingRepository repository;

    @Override
    public Optional<Booking> execute(UUID id) {
        BookingH2Mapper mapper = Mappers.getMapper(BookingH2Mapper.class);
        return repository.findById(id).map(mapper::map);
    }

}
