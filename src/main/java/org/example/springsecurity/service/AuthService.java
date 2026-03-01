package org.example.springsecurity.service;

import org.example.springsecurity.model.Customer;
import org.example.springsecurity.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer register(String name, String email, String password) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(passwordEncoder.encode(password));

        return customerRepository.save(customer);
    }

    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return customer;
    }

    public void recover(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // por ahora solo validamos que exista
    }
}