package com.antonio.superhero.model.specification;

import com.antonio.superhero.model.entity.SuperHero;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SuperHeroSpecification {

    public static Specification<SuperHero> getSuperHero(String filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> orPredicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(filter)) {
                orPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+filter.toLowerCase()+"%"));
                orPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("ability")), "%"+filter.toLowerCase()+"%"));
            }

            final List<Predicate> predicates = new ArrayList<>();
            if (orPredicates.size() > 0) {
                predicates.add(criteriaBuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
