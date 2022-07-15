package br.com.compass.avaliacao4.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Associado {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cargoPolitico;
    private LocalDate dataNascimento;
    private String sexo;
    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Partido partido;
}
