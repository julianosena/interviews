package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Room;
import com.interview.application.gateway.FindRoomsByIdsGateway;
import com.interview.application.gateway.database.h2.model.RoomH2;
import com.interview.application.gateway.database.h2.model.mapper.RoomH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindRoomsByIdsH2Gateway implements FindRoomsByIdsGateway {

    private final EntityManager em;

    @Override
    public List<Room> execute(List<UUID> ids) {
        RoomH2Mapper mapper = Mappers.getMapper(RoomH2Mapper.class);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<RoomH2> criteriaQuery = criteriaBuilder.createQuery(RoomH2.class);
        Root<RoomH2> root = criteriaQuery.from(RoomH2.class);

        Predicate predicate = root.get("id").in(ids);
        root.fetch("hotel", JoinType.INNER);
        root.fetch("type", JoinType.INNER);
        root.fetch("attributes", JoinType.INNER);

        criteriaQuery.where(predicate);

        return em.createQuery(criteriaQuery).getResultList()
                .stream().map(mapper::map).toList();
    }

}
