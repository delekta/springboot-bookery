package pl.delekta.bookery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.delekta.bookery.catalog.application.CatalogController;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.catalog.domain.CatalogService;

import java.util.List;

@SpringBootApplication
public class BookeryOnlineStoreApp {
	public static void main(String[] args) {
		SpringApplication.run(BookeryOnlineStoreApp.class, args);
	}
}
