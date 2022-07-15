package br.com.compass.avaliacao4.dto.request;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class RequestVinculoDto {

    @Positive
    private Long idAssociado;
    @Positive
    private Long idPartido;
}
