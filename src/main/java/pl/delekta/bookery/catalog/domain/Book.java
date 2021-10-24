package pl.delekta.bookery.catalog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
}
