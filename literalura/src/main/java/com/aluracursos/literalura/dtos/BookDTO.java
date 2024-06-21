package com.aluracursos.literalura.dtos;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.model.Language;

import java.util.List;

public record BookDTO(
        String title,
        Long numberOfDownloads,
        String imageUrl,
        List<AuthorDTO> author,
        List<LanguageDTO> language
) {
}
