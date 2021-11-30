package com.antonio.superhero.service.impl;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import com.antonio.superhero.model.dto.out.SuperHeroDTO;
import com.antonio.superhero.model.entity.SuperHero;
import com.antonio.superhero.model.specification.SuperHeroSpecification;
import com.antonio.superhero.repository.SuperHeroRepository;
import com.antonio.superhero.service.SuperHeroService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroRepository;
    private final ConversionService conversionService;
    private Page<SuperHeroDTO> superHeroInCache;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SuperHeroDTO> getAllSuperHeroes(final String filter, final Pageable pageable) {
        boolean saveInCache = false;
        if (StringUtils.isEmpty(filter)) {
            saveInCache = true;
            if (superHeroInCache != null) return superHeroInCache;
        }
        Page<SuperHeroDTO> superHeroInCacheLocal = superHeroRepository.findAll(SuperHeroSpecification.getSuperHero(filter), pageable)
                .map(superHero -> conversionService.convert(superHero, SuperHeroDTO.class));

        if (saveInCache) {
            saveInCache(superHeroInCacheLocal);
        }
        return superHeroInCacheLocal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SuperHeroDTO getSuperHeroByID(final Integer superHeroId) {
        SuperHero superHero = superHeroRepository.findById(superHeroId.longValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Super hero not found"));
        return this.conversionService.convert(superHero, SuperHeroDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SuperHeroDTO updateSuperHero(final Integer superHeroId, final SuperHeroInDTO superHeroInDTO) {
        SuperHero superHeroToUpdate = superHeroRepository.findById(superHeroId.longValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Super hero not found"));
        superHeroToUpdate = superHeroToUpdate.toBuilder()
                .name(superHeroInDTO.getName())
                .ability(superHeroInDTO.getAbility())
                .build();
        superHeroToUpdate = this.superHeroRepository.save(superHeroToUpdate);
        superHeroInCache = null;
        return this.conversionService.convert(superHeroToUpdate, SuperHeroDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteSuperHero(final Integer superHeroId) {
        this.superHeroRepository.deleteById(superHeroId.longValue());
        return true;
    }

    /**
     * Saves in cache the super hero page without a filter
     *
     * @param superHeroInCacheLocal page with all the superheroes without filters
     */
    private void saveInCache(Page<SuperHeroDTO> superHeroInCacheLocal) {
        superHeroInCache = superHeroInCacheLocal;
    }

}
