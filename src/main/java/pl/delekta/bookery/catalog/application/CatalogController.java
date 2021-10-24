package pl.delekta.bookery.catalog.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.catalog.domain.CatalogService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService service;

    public List<Book> findByTitle(String title) {
        return service.findByTitle(title);
    }
}
