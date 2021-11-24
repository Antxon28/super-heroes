package com.antonio.superhero.service;

import com.antonio.superhero.model.dto.SuperHeroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SuperHeroService {

    Page<SuperHeroDTO> getAllSuperHeroes(final String filter, final Pageable pageable);

}
