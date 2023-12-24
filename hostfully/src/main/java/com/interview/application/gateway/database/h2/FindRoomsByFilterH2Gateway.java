package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Room;
import com.interview.application.domain.RoomFilter;
import com.interview.application.gateway.FindRoomsByFilterGateway;
import com.interview.application.gateway.database.h2.model.RoomH2;
import com.interview.application.gateway.database.h2.model.mapper.RoomH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindRoomsByFilterH2Gateway implements FindRoomsByFilterGateway {

    private final EntityManager em;

    @Override
    public List<Room> execute(final RoomFilter filter) {
        RoomH2Mapper mapper = Mappers.getMapper(RoomH2Mapper.class);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<RoomH2> criteriaQuery = criteriaBuilder.createQuery(RoomH2.class);
        Root<RoomH2> root = criteriaQuery.from(RoomH2.class);

        List<Predicate> predicates = new ArrayList<>();
        if(null != filter.getMaxOccupancy()){
            Predicate predicate = criteriaBuilder.equal(root.get("maxOccupancy"), filter.getMaxOccupancy());
            predicates.add(predicate);
        }

        if(null != filter.getIsAvailable()){
            Predicate predicate = criteriaBuilder.equal(root.get("available"), filter.getIsAvailable());
            predicates.add(predicate);
        }

        root.fetch("hotel", JoinType.INNER);
        root.fetch("type", JoinType.INNER);
        root.fetch("attributes", JoinType.INNER);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList()
                .stream().map(mapper::map).toList();
    }

}
