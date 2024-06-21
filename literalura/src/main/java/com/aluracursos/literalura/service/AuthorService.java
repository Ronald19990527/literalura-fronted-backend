package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dtos.AuthorDTO;
import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    private List<AuthorDTO> toListAuthors(List<Author> authors) {
        return authors.stream()
                .map(a -> new AuthorDTO(a.getName(), a.getBirthYear(), a.getDeathYear()))
                .collect(Collectors.toList());
    }

    public List<AuthorDTO> getAllAuthors() {
        return toListAuthors(authorRepository.getAllAuthors());
    }

    public List<AuthorDTO> getAuthorsAliveInACertainYear(Long year) {
        return toListAuthors(authorRepository.getAuthorsAliveInACertainYear(year));
    }
}
