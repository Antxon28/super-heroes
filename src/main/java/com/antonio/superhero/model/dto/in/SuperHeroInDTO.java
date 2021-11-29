package com.antonio.superhero.model.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Builder
@Data
public class SuperHeroInDTO {

    @JsonProperty(value = "name", required = true)
    @Valid
    @NotBlank(message = "Superhero name cannot be empty")
    private String name;

    @JsonProperty(value = "ability", required = true)
    @Valid
    @NotBlank(message = "Superhero ability cannot be empty")
    private String ability;
}
