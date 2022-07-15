package br.com.compass.avaliacao4.dto.response;

import lombok.Data;

@Data
public class ResponsePartidoDto {

    private long id;
    private String nome;
    private String sigla;
    private String Ideologia;
    private String dataFundacao;

}
