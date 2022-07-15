package br.com.compass.avaliacao4.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPartidoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String sigla;
    @NotBlank
    private String Ideologia;
    @NotBlank
    private String dataFundacao;


}
