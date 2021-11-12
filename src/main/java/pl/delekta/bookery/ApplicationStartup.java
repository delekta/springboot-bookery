package pl.delekta.bookery;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase.CreateBookCommand;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase.UpdateBookCommand;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase.UpdateBookResponse;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.order.application.port.PlaceOrderUseCase;
import pl.delekta.bookery.order.application.port.PlaceOrderUseCase.PlaceOrderResponse;
import pl.delekta.bookery.order.application.port.PlaceOrderUseCase.PlaceOrderCommand;
import pl.delekta.bookery.order.application.port.QueryOrderUseCase;
import pl.delekta.bookery.order.domain.OrderItem;
import pl.delekta.bookery.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {
    private final CatalogUseCase catalog;
    private final PlaceOrderUseCase placeOrder;
    private final QueryOrderUseCase queryOrder;

    @Value("${bookery.book.title:Pan}")
    private String title;

    @Value("${bookery.book.author:Pan}")
    private String author;

    @Value("${bookery.book.limit:2}")
    private Long limit;

    @Override
    public void run(String... args) throws Exception {
        initData();
        searchCatalog();
        placeOrder();
    }

    private void placeOrder() {
        Book book1 = catalog.findOneByTitle("Zostan").orElseThrow(() -> new IllegalArgumentException("Cannot find a book"));
        Book book2 = catalog.findOneByTitle("Elon").orElseThrow(() -> new IllegalArgumentException("Cannot find a book"));

        Recipient recipient = Recipient
                .builder()
                .name("Name")
                .phone("Phone")
                .street("Street")
                .city("Krakow")
                .zipCode("ZipCode")
                .email("Email")
                .build();

        PlaceOrderCommand command = PlaceOrderCommand.builder()
                .recipient(recipient)
                .item(new OrderItem(book1.getId(), 16))
                .item(new OrderItem(book2.getId(), 4))
                .build();

        PlaceOrderResponse response = placeOrder.placeOrder(command);
        System.out.println("Created ORDER with id: " + response.getOrderId());

        queryOrder.findAll()
                .forEach(order -> {
//                    System.out.println("Got order with total price: " + order.totalPrice() + " details: " + order);
                });



    }

    private void searchCatalog() {
        findByAuthor();
        findAndUpdate();
        findByAuthor();
    }

    private void initData() {
        catalog.addBook(new CreateBookCommand("Zostan Legenda", "Henryk Sienkiewicz",2000, new BigDecimal(10)));
        catalog.addBook(new CreateBookCommand("Elon Musk - Autobiografia", "Kamil Delekta", 2000, new BigDecimal(20)));
        catalog.addBook(new CreateBookCommand("Incepcja", "Portier Zbychu", 2000, new BigDecimal(30)));
        catalog.addBook(new CreateBookCommand("Jak zjednac sobie ludzi", "Portier Zbychu", 2000, new BigDecimal(40)));
    }

    private void findByAuthor() {
        List<Book> books = catalog.findByAuthor(author);
        books.stream().limit(limit).forEach(System.out::println);
    }

    private void findAndUpdate() {
        catalog.findOneByTitleAndAuthor("Jak", "Portier")
                .ifPresent(book -> {
                        UpdateBookCommand command = UpdateBookCommand.builder()
                                            .id(book.getId())
                                            .title("Zmieniony tytul")
                                            .build();
                        UpdateBookResponse response = catalog.updateBook(command);
                        System.out.println("Updating book result: " + response.isSuccess());
                    }
                );
    }
}
