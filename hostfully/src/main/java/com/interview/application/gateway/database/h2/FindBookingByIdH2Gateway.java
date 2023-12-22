package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByIdGateway;
import com.interview.application.gateway.database.h2.model.BookingH2;
import com.interview.application.gateway.database.h2.model.translator.BookingH2ToRoomTranslator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindBookingByIdH2Gateway implements FindBookingByIdGateway {

    private final EntityManager em;

    @Override
    public Optional<Booking> execute(UUID id) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BookingH2> criteriaQuery = criteriaBuilder.createQuery(BookingH2.class);
        Root<BookingH2> root = criteriaQuery.from(BookingH2.class);

        Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
        root.fetch("roomBookings", JoinType.INNER);

        criteriaQuery.where(predicate);

        try {
            BookingH2 h2 = em.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(BookingH2ToRoomTranslator.translate(h2));

        } catch (NoResultException e){
            log.info("There is no booking with the id {}", id);
            return Optional.empty();
        }
    }

}
