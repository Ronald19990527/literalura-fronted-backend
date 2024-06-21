package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column(name = "number_of_downloads")
    private Long numberOfDownloads;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "books_by_authors",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<Author> author;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "books_by_languages",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id")
    )
    private List<Language> language;

    public Book() {
    }

    public Book(BookDetails bookDetails) {
        this.title = bookDetails.title();
        this.numberOfDownloads = bookDetails.numberOfDownloads();
        this.imageUrl = bookDetails.imageUrlDetails().imgageUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Long numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    @Override
    public String toString() {
        List<String> authorsName = this.author.stream()
                .map(a -> a.getName())
                .collect(Collectors.toList());

        return "\n----- Book -----" +
                "\nTitle: " + this.title +
                "\nAuthors: " + authorsName +
                "\nLanguages: " + this.language +
                "\nImage url: " + this.imageUrl +
                "\nNumber of downloads: " + this.numberOfDownloads;
    }
}