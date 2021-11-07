package pl.delekta.bookery.catalog.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.catalog.domain.CatalogRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogService implements pl.delekta.bookery.catalog.application.port.CatalogUseCase {


    private final CatalogRepository repository;

    public CatalogService(@Qualifier("schoolCatalogRepository") CatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getAuthor().startsWith(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return Optional.empty();
    }

    @Override
    public void addBook() {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void updateBook() {

    }
}