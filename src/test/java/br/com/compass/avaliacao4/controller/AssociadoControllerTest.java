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
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deveria cadastrar um associado.")
    void post() throws Exception {
        URI uri = new URI("/associados");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content
                                (stringJson("oswaldo", "Presidente", "08/02/2000", "Masculino"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));

    }

    @Test
    @DisplayName("Deveria dar erro no campo vazio.")
    void postException() throws Exception {
        URI uri = new URI("/associados");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).content(stringJson("", "Presidente", "08/02/2000", "Masculino"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

        String msg = "{\"validadorDeErros\":[\"O campo: nome must not be blank\"]}";

        Assertions.assertEquals(msg, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("deveria dar erro na data.")
    void postDataException() throws Exception {
        URI uri = new URI("/associados");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(stringJson("oswaldo", "Presidente", "38/02/2000", "Masculino"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

        String msg = "{\"mensagem\":\"Data invalida.\"}";

        Assertions.assertEquals(msg, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Não deveria achar um associado e deveria enviar uma mensagem de erro.")
    void getAssociadoNotFoundException() throws Exception {
        URI uri = new URI("/associados/1000");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();

        String msg = "{\"mensagem\":\"Associado nao encontrado\"}";

        Assertions.assertEquals(msg, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Deveria dar erro no sexo")
    void postSexoException() throws Exception {
        URI uri = new URI("/associados");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(stringJson("oswaldo", "Presidente", "08/02/2000", "Vermelho"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

        String msg = "{\"mensagem\":\"Sexo invalido.\"}";

        Assertions.assertEquals(msg, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Deveria dar erro no cargo politico")
    void postCargoException() throws Exception {
        URI uri = new URI("/associados");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(stringJson("oswaldo", "Medico", "08/02/2000", "Masculino"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400))
                .andReturn();

        String msg = "{\"mensagem\":\"Cargo invalido.\"}";

        Assertions.assertEquals(msg, mvcResult.getResponse().getContentAsString());

    }










    private String stringJson(String nome, String cargoPolitico, String dataNascimento, String sexo) {
        return "{" + "\"nome\":" + "\"" + nome + "\"" + ","
                +"\"cargoPolitico\":" + "\"" + cargoPolitico + "\"" + ","
                + "\"dataNascimento\":" + "\"" + dataNascimento + "\"" + ","
                + "\"sexo\":" + "\"" + sexo + "\"" + "}";
    }

}