package pl.delekta.bookery.order.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.order.application.port.QueryOrderUseCase;
import pl.delekta.bookery.order.domain.Order;
import pl.delekta.bookery.order.domain.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {
    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Order> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }

}
