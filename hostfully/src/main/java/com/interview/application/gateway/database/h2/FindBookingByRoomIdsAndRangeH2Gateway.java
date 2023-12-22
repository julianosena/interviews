package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Booking;
import com.interview.application.gateway.FindBookingByRoomIdsAndRangeGateway;
import com.interview.application.gateway.database.h2.model.BookingH2;
import com.interview.application.gateway.database.h2.model.RoomBookingH2;
import com.interview.application.gateway.database.h2.model.translator.BookingH2ToBookingTranslator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindBookingByRoomIdsAndRangeH2Gateway implements FindBookingByRoomIdsAndRangeGateway {

    private final EntityManager em;

    @Override
    public List<Booking> execute(List<UUID> ids, LocalDate checkinDate, LocalDate checkoutDate) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BookingH2> criteriaQuery = criteriaBuilder.createQuery(BookingH2.class);
        Root<BookingH2> root = criteriaQuery.from(BookingH2.class);

        List<Predicate> predicates = new ArrayList<>();

        Join<BookingH2, RoomBookingH2> roomBookingsJoin = root.join("roomBookings", JoinType.INNER);
        Predicate roomIdsPredicate = roomBookingsJoin.get("room").get("id").in(ids);

        Predicate checkinPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("checkinDate"), checkinDate);
        Predicate checkoutPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("checkoutDate"), checkoutDate);

        predicates.add(roomIdsPredicate);
        predicates.add(checkinPredicate);
        predicates.add(checkoutPredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList()
                .stream().map(BookingH2ToBookingTranslator::translate).toList();
    }

}
