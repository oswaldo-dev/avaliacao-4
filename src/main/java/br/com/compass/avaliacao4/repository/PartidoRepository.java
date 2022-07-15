package br.com.compass.avaliacao4.repository;

import br.com.compass.avaliacao4.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

    @Query("SELECT partido FROM Partido partido WHERE (:ideologia IS NULL OR :ideologia = partido.Ideologia)")
    List<Partido> findWithFilters(@Param("ideologia") String ideologia);
}
