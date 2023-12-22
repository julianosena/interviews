package com.interview.application.gateway.database.h2;

import com.interview.application.domain.Block;
import com.interview.application.gateway.FindBlocksByRangeGateway;
import com.interview.application.gateway.database.h2.model.BlockH2;
import com.interview.application.gateway.database.h2.model.mapper.BlockH2Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindBlocksByRangeH2Gateway implements FindBlocksByRangeGateway {

    private final EntityManager em;
    private final BlockH2Mapper mapper;

    @Override
    public List<Block> execute(LocalDate start, LocalDate end) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BlockH2> criteriaQuery = criteriaBuilder.createQuery(BlockH2.class);
        Root<BlockH2> root = criteriaQuery.from(BlockH2.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("start"), start);
        Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("end"), end);

        predicates.add(startDatePredicate);
        predicates.add(endDatePredicate);

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList()
                .stream().map(mapper::map).toList();
    }

}
