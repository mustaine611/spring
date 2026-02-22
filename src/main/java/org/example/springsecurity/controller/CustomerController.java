package org.example.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    // NUEVO: textos del formulario de login traducidos
    @GetMapping("/login")
    public Map<String, String> login(Locale locale) {
        Map<String, String> texts = new HashMap<>();
        texts.put("title", messageSource.getMessage("login.title", null, locale));
        texts.put("username", messageSource.getMessage("login.username", null, locale));
        texts.put("password", messageSource.getMessage("login.password", null, locale));
        texts.put("button", messageSource.getMessage("login.button", null, locale));
        return texts;
    }
}