package org.example.springsecurity.service;

import org.example.springsecurity.model.Customer;
import org.example.springsecurity.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;

    public AuthService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> register(Customer customer) {

        return customerRepository
                .findByEmail(customer.getEmail())
                .flatMap(existing -> Mono.<Customer>error(new RuntimeException("Email already exists")))
                .switchIfEmpty(customerRepository.save(customer));
    }

    public Mono<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}