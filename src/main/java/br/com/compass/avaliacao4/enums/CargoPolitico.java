package br.com.compass.avaliacao4.enums;

public enum CargoPolitico {
    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_FEDERAL("Deputado Federal"),
    DEPUTADO_ESTADUAL("Deputado Estadual"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    PRESIDENTE("Presidente"),
    NENHUM("Nenhum");

    private final String valor;

    CargoPolitico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
