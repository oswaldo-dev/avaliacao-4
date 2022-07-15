package br.com.compass.avaliacao4.util;

import br.com.compass.avaliacao4.exceptions.DataInvaledException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
@Component
public class ValidaData {

    public LocalDate mudaParaISO(String data) {
        try {
            DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            return LocalDate.parse(data, formatoBR);

        } catch (Exception e) {
            throw new DataInvaledException();
        }
    }

    public String mudaParaBr (LocalDate data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
            return data.format(formatter);
        } catch (Exception e) {
            throw new DataInvaledException();
        }
    }
}
