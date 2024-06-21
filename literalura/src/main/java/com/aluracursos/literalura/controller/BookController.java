package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.dtos.BookDTO;
import com.aluracursos.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping()
    List<BookDTO> toListBooks() {
        return bookService.toListBooksRegistered();
    }

    @GetMapping("/title={title}")
    List<BookDTO> toListBooksByTitle(@PathVariable String title) {
        return bookService.toListBooksByTitle(title);
    }

    @GetMapping("/language={languageCode}")
    List<BookDTO> toListBooksByLanguage(@PathVariable String languageCode) {
        return bookService.toListBooksByLanguage(languageCode);
    }

    @GetMapping("/author={author}")
    List<String> getListBooksTitles(@PathVariable String author) {
        return bookService.getListBooksTitles(author);
    }
}