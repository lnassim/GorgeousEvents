package com.esiea.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
