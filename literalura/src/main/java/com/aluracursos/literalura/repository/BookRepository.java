package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
    List<Book> searchBooksByTitle(String title);

    @Query("SELECT b FROM Book b")
    List<Book> toListBooksRegistered();

    @Query("SELECT b FROM Book b JOIN b.language l WHERE l.language LIKE %:languageCode%")
    List<Book> toListBooksByLanguage(String languageCode);

    @Query("SELECT b.title FROM Book b JOIN b.author a WHERE a.name LIKE %:author%")
    List<String> getListBooksTitles(String author);
}