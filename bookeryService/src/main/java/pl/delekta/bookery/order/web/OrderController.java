package pl.delekta.bookery.order.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.delekta.bookery.catalog.application.port.CatalogUseCase;
import pl.delekta.bookery.catalog.domain.Book;
import pl.delekta.bookery.order.application.port.PlaceOrderUseCase;
import pl.delekta.bookery.order.application.port.PlaceOrderUseCase.PlaceOrderCommand;
import pl.delekta.bookery.order.application.port.PlaceOrderUseCase.PlaceOrderResponse;
import pl.delekta.bookery.order.application.port.QueryOrderUseCase.CreateOrderCommand;
import pl.delekta.bookery.order.application.port.QueryOrderUseCase;
import pl.delekta.bookery.order.domain.Order;
import pl.delekta.bookery.order.domain.OrderItem;
import pl.delekta.bookery.order.domain.OrderStatus;
import pl.delekta.bookery.order.domain.Recipient;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final QueryOrderUseCase repository;
    private final PlaceOrderUseCase placeOrder;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return repository.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable Long id) {
        repository.removeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody CreateOrderCommand command) {
        placeOrder.placeOrder(command.toPlaceOrderCommand());
    }


    @Data
    static class CreateOrderCommand {
        List<OrderItemCommand> items;
        RecipientCommand recipient;

        PlaceOrderCommand toPlaceOrderCommand() {
            List<OrderItem> orderItems = items
                    .stream()
                    .map(item -> new OrderItem(item.bookId, item.quantity))
                    .collect(Collectors.toList());
            return new PlaceOrderCommand(orderItems, recipient.toRecipient());
        }
    }

    @Data
    static class OrderItemCommand {
        Long bookId;
        int quantity;
    }

    @Data
    static class RecipientCommand {
        String name;
        String phone;
        String street;
        String city;
        String zipCode;
        String email;

        Recipient toRecipient() {
            return new Recipient(name, phone, street, city, zipCode, email);
        }
    }
}
