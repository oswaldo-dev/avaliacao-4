package br.com.compass.avaliacao4.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemErro {

    private String campo;
    private String mensagem;
    private List<String> validadorDeErros;

    public MensagemErro(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public MensagemErro(List<String> validadorDeErros) {
        this.validadorDeErros = validadorDeErros;
    }

    public MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }
}
