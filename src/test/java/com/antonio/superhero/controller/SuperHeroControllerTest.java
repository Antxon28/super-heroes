package com.antonio.superhero.controller;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import com.antonio.superhero.model.dto.out.SuperHeroDTO;
import com.antonio.superhero.service.SuperHeroService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
class SuperHeroControllerTest {

    @MockBean
    SuperHeroService superHeroService;

    @SpyBean
    SuperHeroController superHeroController;

    @Test
    void GIVEN_nothing_WHEN_getAllSuperHeroes_THEN_result_list() {
        String filter = StringUtils.EMPTY;
        Pageable pageable = Pageable.unpaged();
        Page<SuperHeroDTO> superHeroList = new PageImpl(Collections.singletonList(SuperHeroDTO.builder().build()), pageable, 0L);
        Mockito.when(superHeroService.getAllSuperHeroes(filter, pageable)).thenReturn(superHeroList);

        Page<SuperHeroDTO> serviceResponse = superHeroController.getAllSuperHeroes(filter, pageable);
        Assertions.assertNotNull(serviceResponse);
    }

    @Test
    void GIVEN_filter_WHEN_getAllSuperHeroes_THEN_result_list() {
        String filter = "man";
        Pageable pageable = Pageable.unpaged();
        Page<SuperHeroDTO> superHeroList = new PageImpl(Collections.singletonList(SuperHeroDTO.builder().build()), pageable, 0L);
        Mockito.when(superHeroService.getAllSuperHeroes(filter, pageable)).thenReturn(superHeroList);

        Page<SuperHeroDTO> serviceResponse = superHeroController.getAllSuperHeroes(filter, pageable);
        Assertions.assertNotNull(serviceResponse);
    }

    @Test
    void GIVEN_filter_WHEN_getAllSuperHeroes_THEN_empty_list() {
        String filter = "no_existe";
        Pageable pageable = Pageable.unpaged();
        Page<SuperHeroDTO> superHeroList = new PageImpl(Collections.emptyList(), pageable, 0L);
        Mockito.when(superHeroService.getAllSuperHeroes(filter, pageable)).thenReturn(superHeroList);

        Page<SuperHeroDTO> serviceResponse = superHeroController.getAllSuperHeroes(filter, pageable);
        Assertions.assertEquals(0, serviceResponse.getContent().size());
    }

    @Test
    void GIVEN_data_WHEN_getSuperHeroByID_THEN_object() {
        SuperHeroDTO superHero = SuperHeroDTO.builder().build();
        Mockito.when(superHeroService.getSuperHeroByID(Mockito.any())).thenReturn(superHero);

        SuperHeroDTO serviceResponse = superHeroController.getSuperHeroByID(1);
        Assertions.assertNotNull(serviceResponse);
    }

    @Test
    void GIVEN_data_WHEN_getSuperHeroByID_THEN_not_found() {
        Mockito.when(superHeroService.getSuperHeroByID(Mockito.any()))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "error"));

        Assertions.assertThrows(ResponseStatusException.class, () -> superHeroController.getSuperHeroByID(999));
    }

    @Test
    void GIVEN_data_WHEN_updateSuperHero_THEN_OK() {
        SuperHeroDTO superHeroDTO = SuperHeroDTO.builder().build();
        Mockito.when(superHeroService.updateSuperHero(Mockito.any(), Mockito.any())).thenReturn(superHeroDTO);

        Integer superHeroID = 999;
        SuperHeroInDTO superHeroRequestBody = SuperHeroInDTO.builder()
                .name("new_name")
                .ability("new_ability")
                .build();
        SuperHeroDTO serviceResponse = superHeroController.updateSuperHero(superHeroID, superHeroRequestBody);
        Assertions.assertNotNull(serviceResponse);
    }

    @Test
    void GIVEN_data_WHEN_updateSuperHero_THEN_not_found() {
        Mockito.when(superHeroService.updateSuperHero(Mockito.any(), Mockito.any()))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "error"));

        Integer superHeroID = 999;
        SuperHeroInDTO superHeroRequestBody = SuperHeroInDTO.builder()
                .name("new_name")
                .ability("new_ability")
                .build();
        Assertions.assertThrows(ResponseStatusException.class, () -> superHeroController.updateSuperHero(superHeroID, superHeroRequestBody));
    }

}