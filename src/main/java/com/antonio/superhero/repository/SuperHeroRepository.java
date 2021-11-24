package com.antonio.superhero.repository;

import com.antonio.superhero.model.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long>, JpaSpecificationExecutor<SuperHero> {
}
