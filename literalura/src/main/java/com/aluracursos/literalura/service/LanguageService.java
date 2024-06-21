package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dtos.LanguageDTO;
import com.aluracursos.literalura.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.getAllLanguages().stream()
                .map(l -> new LanguageDTO(l.getLanguage()))
                .collect(Collectors.toList());
    }
}
