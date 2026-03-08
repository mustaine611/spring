package org.example.springsecurity.controller;

import org.example.springsecurity.dto.CreateOrderRequest;
import org.example.springsecurity.model.Order;
import org.example.springsecurity.service.OrderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // RF7 registrar pedido
    @PostMapping
    public Mono<Order> createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    // RF8 consultar pedido
    @GetMapping("/{id}")
    public Mono<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    // RF10 seguimiento
    @GetMapping("/{id}/status")
    public Mono<String> getOrderStatus(@PathVariable Long id) {
        return orderService.getOrderStatus(id);
    }

    // listar pedidos
    @GetMapping
    public Flux<Order> getOrders() {
        return orderService.getOrders();
    }
}