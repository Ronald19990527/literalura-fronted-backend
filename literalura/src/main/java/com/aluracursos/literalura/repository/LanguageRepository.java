package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    @Query("SELECT l FROM Book b JOIN b.language l")
    List<Language> getAllLanguages();
}
