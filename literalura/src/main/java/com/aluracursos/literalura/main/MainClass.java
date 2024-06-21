package com.aluracursos.literalura.main;

import com.aluracursos.literalura.errors.ErrorsTypeData;
import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.repository.LanguageRepository;
import com.aluracursos.literalura.service.*;
import com.fasterxml.jackson.core.JsonGenerationException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainClass {
    //Thus Spake Zarathustra: A Book for All and None
    //Beyond Good and Evil
    //The Genealogy of Morals: The Complete Works, Volume Thirteen, edited by Dr. Oscar Levy.
    //The Birth of Tragedy; or, Hellenism and Pessimism
    //The Twilight of the Idols; or, How to Philosophize with the Hammer. The Antichrist: Complete Works, Volume Sixteen
    //The Communist Manifesto
    //The Eighteenth Brumaire of Louis Bonaparte
    //A Contribution to the Critique of Political Economy
    //Ecce Homo: Complete Works, Volume Seventeen
    //Also sprach Zarathustra: Ein Buch für Alle und Keinen
    //Der Briefwechsel zwischen Friedrich Engels und Karl Marx 1844 bis 1883, Erster Band
    //Crime and Punishment
    //Grimms' Fairy Tales
    //The King in Yellow
    //Beowulf: An Anglo-Saxon Epic Poem
    //The King James Version of the Bible
    //Representative English Comedies, v. 1. From the beginnings to Shakespeare
    //The Satires of Juvenal, Persius, Sulpicia, and Lucilius: Literally translated into English prose, with notes, chronological tables, arguments, &c.

    private Scanner teclado = new Scanner(System.in);
    private String baseUrl = "https://gutendex.com/books/?search=";
    private ConvertData convertData = new ConvertData();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private LanguageRepository languageRepository;

    public MainClass(BookRepository bookRepository, AuthorRepository authorRepository, LanguageRepository languageRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.languageRepository = languageRepository;
    }

    public void userPanel() throws JsonGenerationException {
        var option = 0;

        while (option != 6){
            System.out.print(
                    "\n\tList to manage books\n" +
                    "1 - Search book by title\n" +
                    "2 - To list books registered\n" +
                    "3 - To list authors registered\n" +
                    "4 - To list authors alive in a certain year\n" +
                    "5 - To List books by language\n" +
                    "6 - Exit\n" +
                    "Chose the option through your number: ");
            var stringOption = teclado.next();
            teclado.nextLine();

            if (ErrorsTypeData.isNumeberInteger(stringOption)) {
                option = Integer.parseInt(stringOption);

                switch (option) {
                    case 1:
                        searchBookByTitle();

                        break;

                    case 2:
                        toListBooksRegistered();

                        break;

                    case 3:
                        toListAuthorsRegistered();

                        break;

                    case 4:
                        toListAuthorsAliveInACertainYear();

                        break;

                    case 5:
                        toListBooksByLanguage();

                        break;

                    case 6:
                        System.out.println("\nThanks for participating\n");

                        break;

                    default:
                        System.out.println("\nInvalid option");

                        break;
                }
            } else {
                System.out.println("\nInvalid input format, Try it again");
            }
        }
    }

    //-----------------------------------------------Search Book By Title-----------------------------------------------
    private boolean verifyIfBookExists(String booktitle) {
        List<Book> booksListExists = bookRepository.searchBooksByTitle(booktitle);

        if (booksListExists.size() > 0) {
            return true;
        }

        return false;
    }

    private void searchBookByTitle() {
        System.out.print("\nEnter the title of the book you want to search: ");
        String bookTitle = teclado.nextLine();
        System.out.print("\n");

        if (!verifyIfBookExists(bookTitle)) {
            try {
                var bookResults = ApiConsumption.getInformationAboutBook(baseUrl + bookTitle.replace(" ", "+"));
                ListBooksResults listBooksResults = convertData.getDataConverted(bookResults, ListBooksResults.class);

                if (listBooksResults.booksResults().size() > 0) {
                    Book book = new Book(listBooksResults.booksResults().get(0));

                    List<Author> authors = listBooksResults.booksResults().get(0).authors().stream()
                            .map(a -> new Author(a))
                            .collect(Collectors.toList());

                    List<Author> allAuthorsRegistered = authorRepository.getAllAuthors();

                    for (int i = 0; i < allAuthorsRegistered.size(); i++) {
                        for (int j = 0; j < authors.size(); j++) {
                            if (authors.get(j).getName().equalsIgnoreCase(allAuthorsRegistered.get(i).getName())) {
                                authors.remove(j);

                                authors.add(allAuthorsRegistered.get(i));
                            }
                        }
                    }

                    authors.forEach(a -> authorRepository.save(a));

                    book.setAuthor(authors);

                    List<Language> languages = listBooksResults.booksResults().get(0).languages().stream()
                            .map(l -> new Language(l))
                            .collect(Collectors.toList());

                    List<Language> allLanguagesRegistered = languageRepository.getAllLanguages();

                    for (int i = 0; i < allLanguagesRegistered.size(); i++) {
                        for (int j = 0; j < languages.size(); j++) {
                            if (languages.get(j).getLanguage().equalsIgnoreCase(allLanguagesRegistered.get(i).getLanguage())) {
                                languages.remove(j);

                                languages.add(allLanguagesRegistered.get(i));
                            }
                        }
                    }

                    languages.forEach(l -> languageRepository.save(l));

                    book.setLanguage(languages);

                    bookRepository.save(book);

                    System.out.println(listBooksResults.booksResults().get(0));

                    System.out.println("\nSuccessful registration");
                } else {
                    System.out.println("\nNo book data found");
                }
            } catch (JsonGenerationException e) {
                throw new RuntimeException("Error accessing Book" + e.getMessage());
            }
        } else {
            System.out.println("\nThe book already exists, cannot register");
        }
    }

    //-----------------------------------------------To List Books Registered-----------------------------------------------
    private void toListBooks(List<Book> books) {
        if (books.size() > 0) {
            books.forEach(System.out::println);
        } else {
            System.out.println("\nNot Found Books");
        }
    }

    //-----------------------------------------------To List Books Registered-----------------------------------------------
    private void toListBooksRegistered() {
        System.out.print("\n");

        List<Book> booksRegistered = bookRepository.toListBooksRegistered();

        toListBooks(booksRegistered);
    }

    //-----------------------------------------------To List Books By Language-----------------------------------------------
    private void toListBooksByLanguage() {
        System.out.println("\nLanguage Codes Menu");
        MainClassAuxiliary mainClassAuxiliary = new MainClassAuxiliary();
        mainClassAuxiliary.showMenuLanguagesCode();
        System.out.print("Enter your book's language code (for example \"es\" for spanish): ");
        var languageCode = teclado.nextLine();
        System.out.print("\n");

        List<Book> booksByLanguage = bookRepository.toListBooksByLanguage(languageCode);

        toListBooks(booksByLanguage);
    }

    //-----------------------------------------------To List Authors----------------------------------------------------------
    private void toListAuthors(List<Author> authors) {
        if (authors.size() > 0) {
            authors.forEach(a -> System.out.println(a + "\nWritten books: " + bookRepository
                    .getListBooksTitles(a.getName()) + "\n"));
        } else {
            System.out.println("\nNot Found Authors");
        }
    }

    //-----------------------------------------------To List Authors Registered-----------------------------------------------
    private void toListAuthorsRegistered() {
        System.out.print("\n");

        List<Author> authorsRegistered = authorRepository.getAllAuthors();

        toListAuthors(authorsRegistered);
    }

    //-----------------------------------------------To List Authors Alive In A Certain Year-----------------------------------------------
    private void toListAuthorsAliveInACertainYear() {
        System.out.print("\nEnter a year: ");
        String year = teclado.nextLine();
        String doYouWantToTryItAgain = "Yes";

        while (!ErrorsTypeData.isNumeberInteger(year) && !doYouWantToTryItAgain.equalsIgnoreCase("No")) {
            System.out.println("\nError, no valid input");

            System.out.print("\nDo you want to try it again (Yes or No)?: ");
            doYouWantToTryItAgain = teclado.nextLine();

            if (doYouWantToTryItAgain.equalsIgnoreCase("Yes")) {
                System.out.print("\nEnter a year: ");
                year = teclado.nextLine();
            }
        }

        if (ErrorsTypeData.isNumeberInteger(year)) {
            System.out.print("\n");

            List<Author> authorsAliveInACertainYear = authorRepository.getAuthorsAliveInACertainYear(Long.valueOf(year));

            toListAuthors(authorsAliveInACertainYear);
        }
    }
}