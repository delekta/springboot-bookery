package pl.delekta.bookery;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CatalogService {

    private Map<Long, Book> books = new ConcurrentHashMap<>();

    public CatalogService() {
        books.put(1L, new Book(1L, "Pan Tadeusz", "Henryk Sienkiewicz"));
        books.put(2L, new Book(2L, "Pan Wołodyjowski", "Kamil Delekta"));
        books.put(3L, new Book(3L, "Harry Portier", "Portier Zbychu"));
    }

    public List<Book> findByTitle(String title) {
        return books.values()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }
}
