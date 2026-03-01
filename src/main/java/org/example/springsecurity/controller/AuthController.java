package org.example.springsecurity.controller;

import org.example.springsecurity.dto.LoginRequest;
import org.example.springsecurity.dto.RecoverRequest;
import org.example.springsecurity.model.Customer;
import org.example.springsecurity.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Customer register(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password){
        System.out.println("llamando al endopoint register: " + email);
        return authService.register(name, email, password);
    }

    @PostMapping("/login")
    public Customer login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/recover")
    public String recover(@RequestBody RecoverRequest request) {
        System.out.println("llamando al endopoint recover: ");
        authService.recover(request.getEmail());
        return "Recovery process started";
    }
}