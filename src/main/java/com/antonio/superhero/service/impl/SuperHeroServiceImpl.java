package com.antonio.superhero.service.impl;

import com.antonio.superhero.model.dto.SuperHeroDTO;
import com.antonio.superhero.model.specification.SuperHeroSpecification;
import com.antonio.superhero.repository.SuperHeroRepository;
import com.antonio.superhero.service.SuperHeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroRepository;
    private final ConversionService conversionService;

    @Override
    public Page<SuperHeroDTO> getAllSuperHeroes(final String filter, final Pageable pageable) {
        return superHeroRepository.findAll(SuperHeroSpecification.getSuperHero(filter), pageable)
                .map(superHero -> conversionService.convert(superHero, SuperHeroDTO.class));
    }
}
