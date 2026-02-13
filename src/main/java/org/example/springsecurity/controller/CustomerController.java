package org.example.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Endpoint público - No requiere autenticación";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Bienvenido ADMIN 👑";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "Bienvenido USER 👤 o ADMIN";
    }

    @GetMapping("/")
    public String home() {
        return "Home - Requiere autenticación";
    }
}
