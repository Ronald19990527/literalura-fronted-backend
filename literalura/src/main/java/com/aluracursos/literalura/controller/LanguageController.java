package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.dtos.LanguageDTO;
import com.aluracursos.literalura.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping()
    public List<LanguageDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}
