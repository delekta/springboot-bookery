package pl.delekta.bookery.order.domain;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Long id;
    private List<OrderItem> items;
    private Recipient recipient;
    private OrderStatus status;
    private LocalDateTime createdAt;

    BigDecimal totalPrice() {
        return items.stream()
                .map(item -> item.getBook().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
