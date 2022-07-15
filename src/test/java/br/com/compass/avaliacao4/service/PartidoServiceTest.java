package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.dto.request.RequestPartidoDto;
import br.com.compass.avaliacao4.exceptions.IdeologiaNotFoundException;
import br.com.compass.avaliacao4.repository.AssociadoRepository;
import br.com.compass.avaliacao4.repository.PartidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PartidoServiceTest {

    private PartidoService service;
    private ModelMapper modelMapper;
    private PartidoRepository partidoRepository;
    private AssociadoRepository associadoRepository;

    @BeforeEach
    public void setUp() {
        this.service = new PartidoService(partidoRepository, modelMapper, associadoRepository);
    }

    @DisplayName("deveria retornar uma ideologia valida")
    @Test
    public void validaIdeologia() {
        String direita = service.validacaoDeIdeologia("Direita");
        Assertions.assertEquals("Direita", direita);
    }


    @DisplayName("Deveria retornar uma IdeologiaNotFoundException")
    @Test
    void naoValidaIdeologia() {

        Assertions.assertThrows(IdeologiaNotFoundException.class,() -> service.validacaoDeIdeologia("Cima"));
    }

    @DisplayName("deveria retornar uma a ideologia independente do maiúsculo ou minúsculo")
    @Test
    void validaSensitive() {
        String esquerda = service.validacaoDeIdeologia("EsQueRDa");
        Assertions.assertEquals("Esquerda", esquerda);
    }


}
