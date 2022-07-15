package br.com.compass.avaliacao4.config;

import br.com.compass.avaliacao4.util.ValidaData;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class Configuracao {

    private final ValidaData data;

    @Autowired
    public Configuracao(ValidaData data) {
        this.data = data;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> stringParaLocalDate = getStringLocalDateConverter();

        Converter<LocalDate, String> localDateParaString = getLocalDateStringConverter();

        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.createTypeMap(LocalDate.class, String.class);
        modelMapper.addConverter(stringParaLocalDate);
        modelMapper.addConverter(localDateParaString);

        return modelMapper;

    }

    private Converter<LocalDate, String> getLocalDateStringConverter() {
        return new AbstractConverter<LocalDate, String>() {

            protected String convert(LocalDate localDate) {
                return data.mudaParaBr(localDate);
            }
        };
    }

    private Converter<String, LocalDate> getStringLocalDateConverter() {
        return new AbstractConverter<String, LocalDate>() {

            protected LocalDate convert(String string) {
                return data.mudaParaISO(string);
            }
        };
    }


}
