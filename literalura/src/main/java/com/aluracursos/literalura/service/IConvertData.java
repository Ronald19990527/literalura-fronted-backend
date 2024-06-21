package com.aluracursos.literalura.service;

import com.fasterxml.jackson.core.JsonGenerationException;

public interface IConvertData {
    <T> T getDataConverted(String json, Class<T> classes) throws JsonGenerationException;
}
