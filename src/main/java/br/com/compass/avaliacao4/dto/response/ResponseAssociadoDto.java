package br.com.compass.avaliacao4.dto.response;

import lombok.Data;

@Data
public class ResponseAssociadoDto {
    private long id;
    private String nome;
    private String cargo;
    private String dataNascimento;
    private String sexo;
    private String nomePartido;
}
