package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.dtos.AuthorDTO;
import com.aluracursos.literalura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping()
    List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/year={year}")
    List<AuthorDTO> getAuthorsAliveInACertainYear(@PathVariable Long year) {
        return authorService.getAuthorsAliveInACertainYear(year);
    }
}