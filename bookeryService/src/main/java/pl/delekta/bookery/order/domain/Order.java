package pl.delekta.bookery.order.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Order {
    private Long id;

    private List<OrderItem> items;

    private Recipient recipient;

    @Builder.Default
    private OrderStatus status = OrderStatus.NEW;

    private LocalDateTime createdAt;

    public Order(List<OrderItem> items, Recipient recipient) {
        this.items = items;
        this.recipient = recipient;
        this.createdAt = LocalDateTime.now();
    }

//    public BigDecimal totalPrice() {
//        return items.stream()
//                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }

}
