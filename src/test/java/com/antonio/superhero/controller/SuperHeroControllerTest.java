package com.antonio.superhero.controller;

import com.antonio.superhero.model.dto.SuperHeroDTO;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

}