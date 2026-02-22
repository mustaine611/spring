package org.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class CustomerController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/public")
    public String publicEndpoint(Locale locale) {
        return messageSource.getMessage("public.endpoint", null, locale);
    }

    @GetMapping("/admin")
    public String adminEndpoint(Locale locale) {
        return messageSource.getMessage("admin.welcome", null, locale);
    }

    @GetMapping("/user")
    public String userEndpoint(Locale locale) {
        return messageSource.getMessage("user.welcome", null, locale);
    }

    @GetMapping("/")
    public String home(Locale locale) {
        return messageSource.getMessage("home.text", null, locale);
    }
}