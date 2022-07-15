package br.com.compass.avaliacao4.repository;

import br.com.compass.avaliacao4.entities.Associado;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    @Query("SELECT associado FROM Associado associado WHERE (:cargo IS NULL OR :cargo = associado.cargoPolitico)")
    List<Associado> findWithFilters(@Param("cargo") String cargo, Sort sort);

    List<Associado> findByPartido_Id(Long id);
}
