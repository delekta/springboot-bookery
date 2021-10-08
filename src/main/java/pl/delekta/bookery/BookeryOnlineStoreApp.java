package pl.delekta.bookery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookeryOnlineStoreApp implements CommandLineRunner {

	private final CatalogService catalogService;

	public BookeryOnlineStoreApp(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookeryOnlineStoreApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Book> books = catalogService.findByTitle("Pan");
		books.forEach(System.out::println);
	}
}
