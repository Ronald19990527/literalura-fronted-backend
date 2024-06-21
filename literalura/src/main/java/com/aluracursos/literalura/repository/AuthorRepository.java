package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Book b JOIN b.author a")
    List<Author> getAllAuthors();

    @Query("SELECT a FROM Book b JOIN b.author a WHERE a.deathYear >= :year")
    List<Author> getAuthorsAliveInACertainYear(Long year);
}