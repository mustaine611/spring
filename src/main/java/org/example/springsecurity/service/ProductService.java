package org.example.springsecurity.service;

import org.example.springsecurity.model.Product;
import org.example.springsecurity.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<Product> getAllProducts() {
        return repository.findAll();
    }

    public Mono<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Mono<Product> createProduct(Product product) {
        return repository.save(product);
    }

    public Mono<Void> deleteProduct(Long id) {
        return repository.deleteById(id);
    }
}