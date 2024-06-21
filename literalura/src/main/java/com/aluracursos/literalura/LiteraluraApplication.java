/* package com.aluracursos.literalura;

import com.aluracursos.literalura.main.MainClass;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private LanguageRepository languageRepository;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(LiteraluraApplication.class, args);

		ctx.close();
	}

	@Override
	public void run(String... args) throws Exception {
        MainClass principal = new MainClass(bookRepository, authorRepository, languageRepository);
        principal.userPanel();
	}
} */