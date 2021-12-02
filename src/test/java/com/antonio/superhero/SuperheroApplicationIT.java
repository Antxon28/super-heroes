package com.antonio.superhero;

import com.antonio.superhero.model.dto.in.SuperHeroInDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:db-test.properties")
@Sql("/test-h2.sql")
@AutoConfigureMockMvc
public class SuperheroApplicationIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void GIVEN_nothing_WHEN_getAllSuperHeroes_THEN_OK() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/superhero"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
    }

    @Test
    public void GIVEN_filter_WHEN_getAllSuperHeroes_THEN_OK() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/superhero?filter=super"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(result -> result.getResponse().getContentAsString().contains("Superman"))
                .andReturn();
    }

    @Test
    public void GIVEN_id_WHEN_getSuperHero_THEN_OK() throws Exception {
        String json = "{\"id\":1,\"name\":\"Batman\",\"ability\":\"Jump\"}";
        this.mvc.perform(MockMvcRequestBuilders.get("/superhero/1"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(json))
                .andReturn();
    }

    @Test
    public void GIVEN_data_WHEN_updateSuperHero_THEN_OK() throws Exception {
        SuperHeroInDTO superHeroInDTO = SuperHeroInDTO.builder().name("Wolverine").ability("Strong").build();
        String json = "{\"id\":2,\"name\":\"Wolverine\",\"ability\":\"Strong\"}";
        this.mvc.perform(MockMvcRequestBuilders.post("/superhero/2")
                        .content(objectMapper.writeValueAsString(superHeroInDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().json(json))
                .andReturn();
    }

    @Test
    public void GIVEN_id_WHEN_deleteSuperHero_THEN_OK() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/superhero/1"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
    }

}
