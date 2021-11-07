package pl.delekta.bookery.order.domain;

import lombok.Value;
import pl.delekta.bookery.catalog.domain.Book;

@Value
public class OrderItem {
    Book book;
    int quantity;

}
