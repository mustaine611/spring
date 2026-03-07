package org.example.springsecurity.repository;

import org.example.springsecurity.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    Flux<Product> findByName(String name);

}