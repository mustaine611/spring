package org.example.springsecurity.service;

import org.example.springsecurity.dto.CreateOrderRequest;
import org.example.springsecurity.model.Order;
import org.example.springsecurity.repository.OrderRepository;
import org.example.springsecurity.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Mono<Order> createOrder(CreateOrderRequest request) {

        return productRepository.findById(request.getProductId())
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
                .flatMap(product -> {

                    Order order = new Order(
                            product.getId(),
                            request.getQuantity(),
                            "PREPARING"
                    );

                    return orderRepository.save(order);
                });
    }

    public Mono<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Mono<String> getOrderStatus(Long id) {
        return orderRepository.findById(id)
                .map(Order::getStatus);
    }
}