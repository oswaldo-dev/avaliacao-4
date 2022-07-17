package br.com.compass.avaliacao4.repository;

import br.com.compass.avaliacao4.entities.Associado;
import br.com.compass.avaliacao4.entities.Partido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class AssociadoRepositoryTest {

    @Autowired
    private AssociadoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria buscar um associado pelo cargo politico")
    void findWithFilters() {
        Associado associado = Associado.builder().nome("Associado").cargoPolitico("Presidente")
                .dataNascimento(LocalDate.now()).sexo("Masculino").build();
        em.persist(associado);

        List<Associado> presidente = repository.findWithFilters("Presidente", Sort.by(Sort.Direction.ASC, "id"));
        Associado DB = presidente.get(0);

        Assertions.assertNotNull(DB);
        Assertions.assertEquals("Presidente", DB.getCargoPolitico());
    }

    @Test
    @DisplayName("Deveria buscar um associado pelo partido")
    void findByPartido_Id() {

        Partido partido = Partido.builder().nome("Partido").dataFundacao(LocalDate.now()).sigla("P").Ideologia("Esquerda").build();
        em.persist(partido);

        Associado associado = Associado.builder().nome("Associado").cargoPolitico("Presidente").partido(partido)
                .dataNascimento(LocalDate.now()).sexo("Masculino").build();
        em.persist(associado);

        List<Associado> partido_id = repository.findByPartido_Id(associado.getPartido().getId());
        Associado DB = partido_id.get(0);

        Assertions.assertNotNull(DB);
        Assertions.assertEquals(1, DB.getId());
    }
}