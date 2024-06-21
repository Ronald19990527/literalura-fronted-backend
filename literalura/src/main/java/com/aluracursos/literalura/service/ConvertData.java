package com.aluracursos.literalura.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getDataConverted(String json, Class<T> classes) throws JsonGenerationException {
        try {
            return objectMapper.readValue(json, classes);
        } catch (JsonMappingException e) {
            throw new RuntimeException("Error mapping class" + e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error mapping class" + e.getMessage());
        }
    }
}
