package pl.delekta.bookery.order.application.port;

import lombok.Value;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.order.domain.Order;
import pl.delekta.bookery.order.domain.OrderItem;
import pl.delekta.bookery.order.domain.Recipient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface QueryOrderUseCase {
    List<Order> findAll();
    Optional<Order> getById(Long id);

    void removeById(Long id);

    @Value
    class CreateOrderCommand {
        List<OrderItem> items;
        Recipient recipient;

        public Order toOrder() {
            return new Order(items, recipient);
        }
    }
}

