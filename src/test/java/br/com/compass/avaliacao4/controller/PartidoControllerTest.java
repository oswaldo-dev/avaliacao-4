package br.com.compass.avaliacao4.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class PartidoControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void cadastra() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(getString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

    }

    @Test
    void cadastraException() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(getStringException())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }


    private String getString() {
        return "{\"nome\":\"abc\"," +
                "\"sigla\":\"asd\"," +
                "\"ideologia\":\"Direita\"," +
                "\"dataFundacao\":\"25/10/2000\"}";
    }

    private String getStringException() {
        return "{\"nome\":\"\"," +
                "\"sigla\":\"asd\"," +
                "\"ideologia\":\"Direita\"," +
                "\"dataFundacao\":\"25/10/2000\"}";
    }
}