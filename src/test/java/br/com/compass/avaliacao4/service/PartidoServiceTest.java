package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.dto.request.RequestPartidoDto;
import br.com.compass.avaliacao4.entities.Partido;
import br.com.compass.avaliacao4.exceptions.IdeologiaNotFoundException;
import br.com.compass.avaliacao4.repository.AssociadoRepository;
import br.com.compass.avaliacao4.repository.PartidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PartidoServiceTest {

    @InjectMocks
    private PartidoService service;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PartidoRepository partidoRepository;
    @Mock
    private AssociadoRepository associadoRepository;
    private Partido partido;
    private RequestPartidoDto partidoDto;


    @BeforeEach
    private void  setUp() {
        this.partido = new Partido();
        this.partidoDto = RequestPartidoDto.builder()
                .nome("Partido")
                .sigla("P")
                .Ideologia("Direita")
                .dataFundacao("12/10/2010")
                .build();
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

    @Test
    @DisplayName("Deveria atualizar um partido.")
    void atualizaPartido() {
        Mockito.when(partidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.partido));

        service.atualizar(this.partidoDto, 1L);
        Mockito.verify(partidoRepository).save(this.partido);
    }

    @Test
    @DisplayName("Deveria deletar um partido.")
    void deletarPartido() {
        Mockito.when(partidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.partido));

        service.deletar(1L);
        Mockito.verify(partidoRepository).delete(this.partido);
    }
}
