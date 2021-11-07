package pl.delekta.bookery.order.application.port;

import pl.delekta.bookery.order.domain.Order;

import java.util.List;

public interface QueryOrderUseCase {
    List<Order> findAll();
}

