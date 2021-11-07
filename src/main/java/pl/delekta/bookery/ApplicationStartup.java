package pl.delekta.bookery;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase;
import pl.delekta.bookery.catalog.domain.Book;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogUseCase catalog;

    @Value("${bookery.book.title:Pan}")
    private String title;

    @Value("${bookery.book.author:Pan}")
    private String author;

    @Value("${bookery.book.limit:2}")
    private Long limit;

    @Override
    public void run(String... args) throws Exception {
        initData();
        findByAuthor();
    }

    private void initData() {
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Zostan Legenda", "Henryk Sienkiewicz",2000));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Elon Musk - Autobiografia", "Kamil Delekta", 2000));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Incepcja", "Portier Zbychu", 2000));
        catalog.addBook(new CatalogUseCase.CreateBookCommand("Jak zjednac sobie ludzi", "Portier Zbychu", 2000));
    }

    private void findByAuthor() {
        List<Book> books = catalog.findByAuthor(author);
        books.stream().limit(limit).forEach(System.out::println);
    }
}
