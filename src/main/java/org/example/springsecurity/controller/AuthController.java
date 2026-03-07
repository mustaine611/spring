package org.example.springsecurity.controller;

import org.example.springsecurity.dto.LoginRequest;
import org.example.springsecurity.dto.RecoverRequest;
import org.example.springsecurity.model.Customer;
import org.example.springsecurity.service.AuthService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Mono<Customer> register(@RequestBody Customer customer) {

        System.out.println("Calling register endpoint: " + customer.getEmail());

        return authService.register(customer);
    }

    @PostMapping("/login")
    public Mono<Customer> login(@RequestBody LoginRequest request) {

        return authService
                .findByEmail(request.getEmail())
                .filter(customer -> customer.getPassword().equals(request.getPassword()));
    }

    @PostMapping("/recover")
    public Mono<String> recover(@RequestBody RecoverRequest request) {

        System.out.println("Calling recover endpoint");

        return authService
                .findByEmail(request.getEmail())
                .map(customer -> "Recovery process started")
                .defaultIfEmpty("Email not found");
    }
}