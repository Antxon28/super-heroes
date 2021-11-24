package com.antonio.superhero.model.specification;

import com.antonio.superhero.model.entity.SuperHero;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SuperHeroSpecification {

    public static Specification<SuperHero> getSuperHero(String filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> orPredicates = new ArrayList<>();
            orPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+filter.toLowerCase()+"%"));
            orPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("ability")), "%"+filter.toLowerCase()+"%"));

            return criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
        };
    }
}
