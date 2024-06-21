package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(name = "birth_year")
    private Long birthYear;
    @Column(name = "death_year")
    private Long deathYear;
    @ManyToMany(mappedBy = "author")
    private List<Book> book;

    public Author() {
    }

    public Author(AuthorDetails authorDetails) {
        this.name = authorDetails.name();
        this.birthYear = authorDetails.birthYear();
        this.deathYear = authorDetails.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Long birthYear) {
        this.birthYear = birthYear;
    }

    public Long getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Long deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "\nAuthor: " + this.name +
                "\nBirth year: " + this.birthYear +
                "\nDeath year: " + this.deathYear;
    }
}