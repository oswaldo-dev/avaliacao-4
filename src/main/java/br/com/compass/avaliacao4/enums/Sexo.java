package br.com.compass.avaliacao4.enums;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String valor;
    Sexo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
