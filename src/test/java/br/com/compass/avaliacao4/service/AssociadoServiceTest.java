package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.exceptions.CargoPoliticoNotFoundException;
import br.com.compass.avaliacao4.exceptions.IdeologiaNotFoundException;
import br.com.compass.avaliacao4.exceptions.SexoNotFoundException;
import br.com.compass.avaliacao4.repository.AssociadoRepository;
import br.com.compass.avaliacao4.repository.PartidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class AssociadoServiceTest {

    private AssociadoService service;
    private ModelMapper modelMapper;
    private PartidoRepository partidoRepository;
    private AssociadoRepository associadoRepository;

    @BeforeEach
    public void setUp() {
        this.service = new AssociadoService(associadoRepository, modelMapper, partidoRepository);
    }

    @DisplayName("deveria retornar um cargo politico valida")
    @Test
    void validacaoDeCargoPolitico() {
        String presidente = service.validacaoDeCargoPolitico("Presidente");
        Assertions.assertEquals("Presidente", presidente);
    }

    @DisplayName("Deveria retornar uma CargoPoliticoNotFoundException")
    @Test
    void naoValidaIdeologia() {
        Assertions.assertThrows(CargoPoliticoNotFoundException.class,() -> service.validacaoDeCargoPolitico("Programador"));
    }

    @DisplayName("deveria retornar um cargo politico independente do maiúsculo ou minúsculo")
    @Test
    void validaSensitive() {
        String presidente = service.validacaoDeCargoPolitico("PreSiDEnte");
        Assertions.assertEquals("Presidente", presidente);
    }


    @DisplayName("deveria retornar um sexo valida")
    @Test
    void validacaoDeSexo() {
        String masculino = service.validacaoDeSexo("Masculino");
        Assertions.assertEquals("Masculino", masculino);
    }

    @DisplayName("Deveria retornar uma SexoNotFoundException")
    @Test
    void naoValidaSexo() {
        Assertions.assertThrows(SexoNotFoundException.class,() -> service.validacaoDeSexo("Indefinido"));
    }

    @DisplayName("deveria retornar um sexo independente do maiúsculo ou minúsculo")
    @Test
    void validaSexoSensitive() {
        String presidente = service.validacaoDeSexo("MasCuLiNO");
        Assertions.assertEquals("Masculino", presidente);
    }


}