package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.dto.request.RequestAssociadoDto;
import br.com.compass.avaliacao4.dto.request.RequestVinculoDto;
import br.com.compass.avaliacao4.entities.Associado;
import br.com.compass.avaliacao4.entities.Partido;
import br.com.compass.avaliacao4.exceptions.CargoPoliticoNotFoundException;
import br.com.compass.avaliacao4.exceptions.SexoNotFoundException;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AssociadoServiceTest {
    @InjectMocks
    private AssociadoService service;
    @Mock
    private AssociadoRepository associadoRepository;
    @Mock
    private PartidoRepository partidoRepository;
    @Mock
    private ModelMapper modelMapper;
    private Associado associado;
    private RequestAssociadoDto associadoDto;
    private Partido partido;

    @BeforeEach
    public void setUp() {
        this.associado = new Associado();
        this.partido = new Partido();
        this.associadoDto = RequestAssociadoDto.builder()
                .nome("oswaldo")
                .cargoPolitico("Presidente")
                .dataNascimento("08/02/2000")
                .sexo("Masculino")
                .build();
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

    @Test
    @DisplayName("Deveria atualizar um associado.")
    void atualizaPartido() {
        Mockito.when(associadoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.associado));

        service.atualizar(this.associadoDto, 1L);
        Mockito.verify(associadoRepository).save(this.associado);
    }

    @Test
    @DisplayName("Deveria deletar um associado.")
    void deletarPartido() {
        Mockito.when(associadoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.associado));

        service.deletar(1L);
        Mockito.verify(associadoRepository).delete(this.associado);
    }

    @Test
    @DisplayName("Deveria desvincular um associado a um partido.")
    void demitir() {
        associado.setId(1L);
        partido.setId(1L);
        associado.setPartido(partido);

        Mockito.when(associadoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.associado));
        Mockito.when(partidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.partido));
        Mockito.when(associadoRepository.save(associado)).thenReturn(this.associado);

        service.demitir(associado.getId(), partido.getId());
        Mockito.verify(associadoRepository).save(this.associado);
        Assertions.assertNull(associado.getPartido());

    }

    @Test
    @DisplayName("Deveria vincular um associado a um partido.")
    void vincular() {

        RequestVinculoDto vinculoDto = new RequestVinculoDto();

        vinculoDto.setIdAssociado(1L);
        vinculoDto.setIdPartido(1L);
        associado.setId(1L);
        partido.setId(1L);

        Mockito.when(associadoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.associado));
        Mockito.when(partidoRepository.findById(1L)).thenReturn(Optional.ofNullable(this.partido));
        Mockito.when(associadoRepository.save(associado)).thenReturn(this.associado);


        service.vinculaPartido(vinculoDto);
        Mockito.verify(associadoRepository).save(this.associado);
        Assertions.assertEquals(associado.getPartido(), partido);

    }




}