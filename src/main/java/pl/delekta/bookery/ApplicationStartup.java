package pl.delekta.bookery;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase;
import pl.delekta.bookery.catalog.domain.Book;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogUseCase catalog;

    @Override
    public void run(String... args) throws Exception {
        List<Book> books = catalog.findByTitle("Pan");
        books.forEach(System.out::println);
    }
}
