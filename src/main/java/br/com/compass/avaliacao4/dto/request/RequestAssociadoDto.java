package br.com.compass.avaliacao4.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAssociadoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String cargoPolitico;
    @NotNull
    private String dataNascimento;
    @NotBlank
    private String sexo;
}

