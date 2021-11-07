package pl.delekta.bookery.order.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.delekta.bookery.order.application.port.QueryOrderUseCase;
import pl.delekta.bookery.order.domain.Order;
import pl.delekta.bookery.order.domain.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {
    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}
