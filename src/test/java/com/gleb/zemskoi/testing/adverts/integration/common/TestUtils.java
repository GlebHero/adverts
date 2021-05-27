package com.gleb.zemskoi.testing.adverts.integration.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class TestUtils {

    public static ObjectMapper getSimpleObjectMapper() {
        return Jackson2ObjectMapperBuilder
                .json()
                .autoDetectGettersSetters(true)
                .autoDetectFields(true)
                .failOnUnknownProperties(true)
                .locale(Locale.ENGLISH)
                .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static <T> T getClassPathResourceAsObject(String path, TypeReference<T> reference) {
        try {
            InputStream resource = new ClassPathResource(path).getInputStream();
            return getSimpleObjectMapper().readValue(resource, reference);
        } catch (JsonParseException e) {
            throw new IllegalStateException("Can't parse : " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IllegalStateException("Can't process classpath resource: " + e.getMessage(), e);
        }
    }
}
