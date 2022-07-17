package br.com.compass.avaliacao4.repository;

import br.com.compass.avaliacao4.entities.Partido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class PartidoRepositoryTest {

    @Autowired
    private PartidoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria buscar um partido pela ideologia.")
    void buscaComFiltro() {

        Partido partido = Partido.builder().nome("Partido").Ideologia("Direita").sigla("P").dataFundacao(LocalDate.now()).build();
        em.persist(partido);

        List<Partido> direita = repository.findWithFilters("Direita");
        Partido DB = direita.get(0);

        Assertions.assertNotNull(DB);
        Assertions.assertEquals("Direita", DB.getIdeologia());

    }
}

