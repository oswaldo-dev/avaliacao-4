package br.com.compass.avaliacao4.enums;

public enum Ideologia {
    DIREITA("Direita"),
    ESQUERDA("Esquerda"),
    CENTRO("Centro");

    private final String valor;

    Ideologia(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
