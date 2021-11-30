package com.antonio.superhero.controller;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import com.antonio.superhero.model.dto.out.SuperHeroDTO;
import com.antonio.superhero.service.SuperHeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Retrieves all the superheroes from data base with a filter.")
    public Page<SuperHeroDTO> getAllSuperHeroes(
            @ApiParam(value = "SuperHero search filter", example = "Batman")
            @RequestParam(value = "filter", required = false) final String filter,
            @ApiParam(value = "Pageable parameters") @PageableDefault Pageable pageable) {
        return this.superHeroService.getAllSuperHeroes(filter, pageable);
    }

    /**
     * Retrieves all the information from a super hero.
     *
     * @param superHeroId super hero identification
     * @return super hero information.
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retrieves all the information from a superhero.")
    public SuperHeroDTO getSuperHeroByID(
            @ApiParam(value = "Super hero identification", example = "1", required = true)
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
    @ApiOperation(value = "Updates the information from a superhero")
    public SuperHeroDTO updateSuperHero(
            @ApiParam(value = "Super hero identification", example = "1", required = true)
            @PathVariable(value = "id", required = true) final Integer superHeroId,
            @ApiParam(value = "New super hero data") @Valid @RequestBody(required = true) final SuperHeroInDTO superHeroIn) {
        return this.superHeroService.updateSuperHero(superHeroId, superHeroIn);
    }

    /**
     * Removes a super hero from the database
     *
     * @param superHeroId super hero identification to remove
     * @return response super removed or not
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Removes a superhero")
    public Boolean deleteSuperHero(
            @ApiParam(value = "Super hero identification", example = "1", required = true)
            @PathVariable(value = "id", required = true) final Integer superHeroId) {
        return this.superHeroService.deleteSuperHero(superHeroId);
    }
}
