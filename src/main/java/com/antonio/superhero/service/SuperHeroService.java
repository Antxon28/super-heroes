package com.antonio.superhero.service;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import com.antonio.superhero.model.dto.out.SuperHeroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SuperHeroService {

    /**
     * Retrieves all the super hero list and looks for a filter.
     *
     * @param filter filter to search a super hero by data
     * @param pageable pagination configuration
     * @return pageable super hero list
     */
    Page<SuperHeroDTO> getAllSuperHeroes(final String filter, final Pageable pageable);

    /**
     * Retrieves all the information from a super hero.
     *
     * @param superHeroId super hero identification
     * @return super hero information.
     */
    SuperHeroDTO getSuperHeroByID(final Integer superHeroId);

    /**
     * Updates the information from a super hero information
     *
     * @param superHeroId super hero to update
     * @param superHeroIn new super hero information
     * @return superhero updated with new information
     */
    SuperHeroDTO updateSuperHero(final Integer superHeroId, final SuperHeroInDTO superHeroIn);

}
