package com.antonio.superhero.model.converter;

import com.antonio.superhero.model.dto.SuperHeroDTO;
import com.antonio.superhero.model.entity.SuperHero;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperHeroToSuperHeroDTO implements Converter<SuperHero, SuperHeroDTO> {

    @Override
    public SuperHeroDTO convert(SuperHero source) {

        if (source == null) {
            return null;
        }

        return SuperHeroDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .ability(source.getAbility())
                .build();
    }

}
