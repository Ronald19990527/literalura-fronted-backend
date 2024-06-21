package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDetails(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Long birthYear,
        @JsonAlias("death_year") Long deathYear
) {
}