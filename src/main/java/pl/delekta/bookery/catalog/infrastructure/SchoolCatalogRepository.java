package pl.delekta.bookery.catalog.infrastructure;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class SchoolCatalogRepository implements CatalogRepository {
    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public SchoolCatalogRepository() {
        storage.put(1L, new Book(1L, "Pan Tadeusz", "Henryk Sienkiewicz"));
        storage.put(2L, new Book(2L, "Pan Wołodyjowski", "Kamil Delekta"));
        storage.put(3L, new Book(3L, "Harry Portier", "Portier Zbychu"));
        storage.put(4L, new Book(4L, "Pan Paweł", "Portier Zbychu"));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
}
