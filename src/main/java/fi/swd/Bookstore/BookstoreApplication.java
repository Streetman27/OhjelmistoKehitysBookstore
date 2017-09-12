package fi.swd.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.swd.Bookstore.domain.Book;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookRepository.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			// save a couple of books
			repository.save(new Book("A Farewell to Arms", "Ernest Hemingway","1929","1232323-21"));
			repository.save(new Book("Animal Farm", "George Orwell","1945","2212343-5"));

			// fetch all books
			log.info("Books found with findAll():");
			log.info("-------------------------------");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("");

			// fetch an individual book by ID
			Book book = repository.findOne(1L);
			log.info("Book found with findOne(1L):");
			log.info("--------------------------------");
			log.info(book.toString());
			log.info("");

			// fetch books by author
			log.info("Book found with findByAuthor('George Orwell'):");
			log.info("--------------------------------------------");
			for (Book author : repository.findByAuthor("George Orwell")) {
				log.info(author.toString());
			}
			log.info("");
		};
	}

}
