package br.com.compass.avaliacao4.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String nome;
    @Column(unique=true)
    private String sigla;
    private String Ideologia;
    private LocalDate dataFundacao;
    @OneToMany(mappedBy = "partido")
    private List<Associado> associados = new ArrayList<>();
}

