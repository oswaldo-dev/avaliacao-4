package br.com.compass.avaliacao4.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
    @DisplayName("Deveria cadastrar um partido.")
    void cadastra() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(stringJson("Partido", "P", "Esquerda", "10/10/2010"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

    }

    @Test
    @DisplayName("Deveria dar erro na ideologia.")
    void cadastraIdeologiaException() throws Exception {
        URI uri = new URI("/partidos");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(stringJson("Partido", "P", "Cima", "10/10/2010"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400)).andReturn();

        Assertions.assertEquals("{\"mensagem\":\"Ideologia invalida.\"}", mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("não deveria encontrar um partido e deveria enviar uma mensagem de erro")
    void getPartidoNotFoundException() throws Exception {
        URI uri = new URI("/partidos/1000");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();

        String msg = "{\"mensagem\":\"Partido nao encontrado.\"}";

        Assertions.assertEquals(msg, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Deveria dar erro no campo em branco")
    void cadastraException() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(stringJson("", "P", "Direita", "10/10/2010"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }






    private String stringJson(String nome, String sigla, String ideologia, String dataFundacao) {
        return "{" + "\"nome\":" + "\"" + nome + "\"" + ","
                +"\"sigla\":" + "\"" + sigla + "\"" + ","
                + "\"ideologia\":" + "\"" + ideologia + "\"" + ","
                + "\"dataFundacao\":" + "\"" + dataFundacao + "\"" + "}";
    }
}