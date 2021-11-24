package com.antonio.superhero.controller;

import com.antonio.superhero.model.dto.SuperHeroDTO;
import com.antonio.superhero.service.SuperHeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/superhero")
public class SuperHeroController {

    private final SuperHeroService superHeroService;

    @GetMapping()
    public Page<SuperHeroDTO> getAllSuperHeroes(
            @RequestParam(value = "filter", required = false) final String filter,
            @PageableDefault Pageable pageable) {
        return this.superHeroService.getAllSuperHeroes(filter, pageable);
    }
}
