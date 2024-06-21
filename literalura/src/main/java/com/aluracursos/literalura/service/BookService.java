package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dtos.AuthorDTO;
import com.aluracursos.literalura.dtos.BookDTO;
import com.aluracursos.literalura.dtos.LanguageDTO;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    private List<BookDTO> toListBooks(List<Book> books) {
        if (books.size() > 0) {
            return books.stream()
                    .map(b -> new BookDTO(b.getTitle(), b.getNumberOfDownloads(), b.getImageUrl(), b.getAuthor()
                            .stream()
                            .map(a -> new AuthorDTO(a.getName(), a.getBirthYear(), a.getDeathYear()))
                            .collect(Collectors.toList()), b.getLanguage()
                            .stream()
                            .map(l -> new LanguageDTO(l.getLanguage()))
                            .collect(Collectors.toList())))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<BookDTO> toListBooksRegistered() {
        return toListBooks(bookRepository.toListBooksRegistered());
    }

    public List<BookDTO> toListBooksByTitle(String title) {
        return toListBooks(bookRepository.searchBooksByTitle(title));
    }

    public List<BookDTO> toListBooksByLanguage(String languageCode) {
        return toListBooks(bookRepository.toListBooksByLanguage(languageCode));
    }

    public List<String> getListBooksTitles(String author) {
        return bookRepository.getListBooksTitles(author);
    }
}
