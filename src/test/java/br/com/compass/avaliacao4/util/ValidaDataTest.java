package br.com.compass.avaliacao4.util;

import br.com.compass.avaliacao4.exceptions.DataInvaledException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidaDataTest {

    private ValidaData data;

    @BeforeEach
    public void setUp() {
        this.data = new ValidaData();
    }


    @DisplayName("Deveria retornar uma data no formato ISO")
    @Test
    void mudaParaISO() {
        LocalDate localDate = data.mudaParaISO("11/02/2015");
        LocalDate dataTeste = LocalDate.of(2015, 2, 11);

        assertEquals(dataTeste, localDate);

    }

    @DisplayName("Deveria lançar uma DataInvalidException e caso de data errada")
    @Test
    void informaDataErrada() {
        Assertions.assertThrows(DataInvaledException.class,() -> data.mudaParaISO("11/13/2005"));
    }

    @DisplayName("Deveria retornar uma data no formato BR")
    @Test
    void mudaParaBr() {
        String localDate = data.mudaParaBr(LocalDate.of(2022, 7, 15));
        String dataTeste = "15/07/2022";

        Assertions.assertEquals(dataTeste, localDate);

    }

}