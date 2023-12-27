package com.interview.application.api.model.fixture.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class HttpBodyMapper<T> {

    private static final ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

    @SneakyThrows
    public String map(T t) {
        return mapper.writeValueAsString(t);
    }

    @SneakyThrows
    public T map(String value, TypeReference<T> typeReference) {
        return mapper.readValue(value, typeReference);
    }

}
