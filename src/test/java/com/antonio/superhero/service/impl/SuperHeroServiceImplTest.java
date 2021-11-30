package com.antonio.superhero.service.impl;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import com.antonio.superhero.model.dto.out.SuperHeroDTO;
import com.antonio.superhero.model.entity.SuperHero;
import com.antonio.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class SuperHeroServiceImplTest {

    @MockBean
    SuperHeroRepository superHeroRepository;

    @MockBean
    ConversionService conversionService;

    @SpyBean
    SuperHeroServiceImpl superHeroServiceImpl;

    @Test
    void GIVEN_data_without_filter_WHEN_getAllSuperHeroes_THEN_ok() {
        Page<SuperHero> superHeroList = new PageImpl(Collections.singletonList(SuperHero.builder().name("AAA").build()), Pageable.unpaged(), 0L);
        Mockito.when(superHeroRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(superHeroList);
        SuperHeroDTO superHeroDto = SuperHeroDTO.builder().build();
        Mockito.when(conversionService.convert(Mockito.any(), Mockito.any()))
                .thenReturn(superHeroDto);

        Page<SuperHeroDTO> page = superHeroServiceImpl.getAllSuperHeroes("", Pageable.unpaged());
        Assertions.assertNotNull(page);
    }

    @Test
    void GIVEN_data_with_filter_WHEN_getAllSuperHeroes_THEN_ok() {
        Page<SuperHero> superHeroList = new PageImpl(Collections.singletonList(SuperHero.builder().name("AAA").build()), Pageable.unpaged(), 0L);
        Mockito.when(superHeroRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(superHeroList);
        SuperHeroDTO superHeroDto = SuperHeroDTO.builder().build();
        Mockito.when(conversionService.convert(Mockito.any(), Mockito.any()))
                .thenReturn(superHeroDto);

        Page<SuperHeroDTO> page = superHeroServiceImpl.getAllSuperHeroes("super", Pageable.unpaged());
        Assertions.assertNotNull(page);
    }

    @Test
    void GIVEN_data_WHEN_getSuperHeroByID_THEN_ok() {
        Optional<SuperHero> superHero = Optional.of(SuperHero.builder().build());
        Mockito.when(superHeroRepository.findById(Mockito.any())).thenReturn(superHero);
        SuperHeroDTO superHeroDto = SuperHeroDTO.builder().build();
        Mockito.when(conversionService.convert(Mockito.any(), Mockito.any()))
                .thenReturn(superHeroDto);

        SuperHeroDTO superHeroDTO = superHeroServiceImpl.getSuperHeroByID(1);
        Assertions.assertNotNull(superHeroDTO);
    }

    @Test
    void GIVEN_data_WHEN_getSuperHeroByID_THEN_error() {
        Mockito.when(superHeroRepository.findById(Mockito.any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "error"));

        Assertions.assertThrows(ResponseStatusException.class, () -> superHeroServiceImpl.getSuperHeroByID(1000));
    }

    @Test
    void GIVEN_data_WHEN_updateSuperHero_THEN_ok() {
        Optional<SuperHero> superHero = Optional.of(SuperHero.builder().build());
        Mockito.when(superHeroRepository.findById(Mockito.any())).thenReturn(superHero);
        Mockito.when(superHeroRepository.save(Mockito.any())).thenReturn(SuperHero.builder().build());
        SuperHeroDTO superHeroDto = SuperHeroDTO.builder().build();
        Mockito.when(conversionService.convert(Mockito.any(), Mockito.any()))
                .thenReturn(superHeroDto);

        SuperHeroInDTO superHeroInDTO = SuperHeroInDTO.builder().name("New").ability("Strong").build();
        SuperHeroDTO superHeroDTO = superHeroServiceImpl.updateSuperHero(1, superHeroInDTO);
        Assertions.assertNotNull(superHeroDTO);
    }

    @Test
    void GIVEN_data_WHEN_updateSuperHero_THEN_error() {
        Mockito.when(superHeroRepository.findById(Mockito.any())).thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "error"));

        SuperHeroInDTO superHeroInDTO = SuperHeroInDTO.builder().name("New").ability("Strong").build();
        Assertions.assertThrows(ResponseStatusException.class, () -> superHeroServiceImpl.updateSuperHero(1000, superHeroInDTO));
    }

    @Test
    void GIVEN_data_WHEN_deleteSuperHero_THEN_ok() {
        Boolean response = superHeroServiceImpl.deleteSuperHero(1);
        Assertions.assertTrue(response);
        Mockito.verify(superHeroRepository).deleteById(Mockito.any());
    }

}