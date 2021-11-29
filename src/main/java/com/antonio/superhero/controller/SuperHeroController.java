package com.antonio.superhero.controller;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import com.antonio.superhero.model.dto.out.SuperHeroDTO;
import com.antonio.superhero.service.SuperHeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/superhero")
public class SuperHeroController {

    private final SuperHeroService superHeroService;

    /**
     * Retrieves all the super hero list and looks for a filter.
     *
     * @param filter filter to search a super hero by data
     * @param pageable pagination configuration
     * @return pageable super hero list
     */
    @GetMapping()
    public Page<SuperHeroDTO> getAllSuperHeroes(
            @RequestParam(value = "filter", required = false) final String filter,
            @PageableDefault Pageable pageable) {
        return this.superHeroService.getAllSuperHeroes(filter, pageable);
    }

    /**
     * Retrieves all the information from a super hero.
     *
     * @param superHeroId super hero identification
     * @return super hero information.
     */
    @GetMapping(value = "/{id}")
    public SuperHeroDTO getSuperHeroByID(
            @PathVariable(value = "id", required = true) final Integer superHeroId) {
        return this.superHeroService.getSuperHeroByID(superHeroId);
    }

    /**
     * Updates the information from a super hero information
     *
     * @param superHeroId super hero to update
     * @param superHeroIn new super hero information
     * @return superhero updated with new information
     */
    @PostMapping(value = "/{id}")
    public SuperHeroDTO updateSuperHero(
            @PathVariable(value = "id", required = true) final Integer superHeroId,
            @Valid @RequestBody(required = true) final SuperHeroInDTO superHeroIn) {
        return this.superHeroService.updateSuperHero(superHeroId, superHeroIn);
    }
}
